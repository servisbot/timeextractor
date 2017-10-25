import os
import json
from numbers import Number
from collections import Iterable, Mapping
from operator import itemgetter

from .config import PATH_TO_JAR, JavaSettingsConstructorParams

os.environ['CLASSPATH'] = PATH_TO_JAR

from jnius import autoclass, MetaJavaClass
import jnius

# Java DataTypes
jMap = jnius.autoclass('java.util.HashMap')
jArrayList = jnius.autoclass('java.util.ArrayList')
jList = jnius.autoclass('java.util.List')
jInt = jnius.autoclass('java.lang.Integer')
jLong = jnius.autoclass('java.lang.Long')
jFloat = jnius.autoclass('java.lang.Float')
jDouble = jnius.autoclass('java.lang.Double')
jString = jnius.autoclass('java.lang.String')
jBoolean = jnius.autoclass('java.lang.Boolean')

# Custom Java Classes
Settings = autoclass('ai.digamma.entities.Settings')
Service = autoclass('ai.digamma.service.DateTimeExtractor')
SettingsBuilder = autoclass('ai.digamma.utils.SettingsBuilder')


class JavaPrimitive(object):
    '''
    Convert primitives to their corresponding Java-types based on size
    '''
    def __return_value(self, javaObj, isValue, attr):
        if isValue:
            return getattr(javaObj, attr)()
        else:
            return javaObj


    def __call__(self, obj, isValue = False):
        if isinstance(obj, int):

            if isinstance(obj, bool):
                return jBoolean(obj)

            if obj <= jInt.MAX_VALUE:
                return self.__return_value(jInt(obj), isValue, 'intValue')
            else:
                return self.__return_value(jLong(obj), isValue, 'longValue')

        elif isinstance(obj, float):
            if obj < jFloat.MAX_VALUE:
                return self.__return_value(jFloat(obj), isValue, 'floatValue')
            else:
                return self.__return_value(jDouble(obj), isValue, 'doubleValue')

        elif isinstance(obj, str):
            return jString(obj)


class JavaComposite(object):

    def __init__(self):
        self.primitives = (Number, str)
        self.primitiveConverter = JavaPrimitive()

    def __call__(self, obj, isValue = False):
        '''
        Recursively convert Python objects to composite Java oobjects (e.g. Java Map<String, Object>)
        :param obj: Python object
        '''
        try:
            if isinstance(obj, self.primitives):
                return self.primitiveConverter(obj, isValue)

            elif isinstance(obj.__class__, MetaJavaClass):
                return obj

            elif isinstance(obj, Mapping):
                HashMap = jMap()
                for key, value in obj.items():
                    hashMapKey = self(key, isValue)
                    hashMapValue = self(value, isValue = False)
                    HashMap.put(hashMapKey, hashMapValue)
                return HashMap

            elif isinstance(obj, Iterable):
                JavaArrayList = jArrayList()
                for element in obj:
                    temp = self(element)
                    JavaArrayList.add(temp)
                return JavaArrayList

            else:
                return jString(str(obj))
        except Exception as e:
            print(repr(e))
            raise e


class PySettings(object):
    JavaSettings = Settings
    Converter = JavaComposite()

    def __init__(self, **kwargs):
        self.kwargs = kwargs

    def __call__(self):
        if self.kwargs:
            temp = dict()
            for param, value in self.kwargs.items():
                temp[param] = self.Converter(value, isValue = True)
            JavaParams = itemgetter(*JavaSettingsConstructorParams)(temp)
            return self.JavaSettings(*JavaParams)
        else:
            return self.JavaSettings()


class PySettingsBuilder(object):
    JavaSettingsBuilder = SettingsBuilder
    Converter = JavaComposite()

    def __init__(self):
        self.javaBuilderObj = self.JavaSettingsBuilder()


    def __getattr__(self, attr):
        if hasattr(self.javaBuilderObj, attr):
            def wrapper(*args, **kwargs):
                args = [self.Converter(arg, isValue=True) for arg in args]
                for key, value in kwargs.items():
                    kwargs[key] = self.Converter(value, isValue=True)
                return getattr(self.javaBuilderObj, attr)(*args, **kwargs)
            return wrapper
        raise AttributeError(attr)


class ExtractionService(object):
    JavaService = Service
    Converter = JavaComposite()

    @classmethod
    def extract(cls, text, **kwargs):
        kwargs.update({'text': text})
        if 'settings' in kwargs:
            if not isinstance(kwargs['settings'].__class__, MetaJavaClass) and callable(kwargs['settings']):
                kwargs['settings'] = kwargs['settings']()
            ServiceParams = [cls.Converter(el) for el in itemgetter('text', 'settings')(kwargs)]
        else:
            ServiceParams = (cls.Converter(kwargs['text']),)
        rez = cls.JavaService.extractJSON(*ServiceParams)
        return json.loads(rez)


if __name__=='__main__':
    settings = (PySettingsBuilder()
            .addRulesGroup('DurationGroup')
            .excludeRules("holidaysRule")
            .addUserDate("2017-10-23T18:40:40.931Z")
            .addTimeZoneOffset("100")
            .includeOnlyLatestDates(True)
            .build()
           )
    text = "10-15 month"
    rez = ExtractionService.extract(text=text, settings=settings)
    print(rez)

