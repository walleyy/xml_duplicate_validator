package org.siloW;

import org.siloW.error.DuplicatedXmlField;
import org.siloW.error.IncorrectXml;
import org.siloW.error.RequiredParameter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class Main {

    /** This method should be used after verifying payload provided(xml), is correct.
     *  @implNote To call this method simply pass the xml and a new instance of class:
     *  @param xml The xml string to be checked
     *  @param theObject The new instance of class(POJO)
     *
     *  @throws DuplicatedXmlField When duplicates are found
     *  @throws ClassNotFoundException When you pass a class param not instantiated
     *  @code findDuplicate(xml, new PoJo())*/
    public static void findDuplicate( String xml, Object theObject) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if(xml == null ){
            throw new RequiredParameter("xml input required");
        }

        if(theObject == null){
            throw new RequiredParameter("reference class object is required");
        }

        for (Field field : theObject.getClass().getDeclaredFields()) {
            if(isJavaOrCustom(field.getType())){
                if(List.class == field.getType()){

                    ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
                    Class<?> genClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                    Object newClass = Class.forName(genClass.getName()).getDeclaredConstructor().newInstance();
                    String className = newClass.getClass().getSimpleName();
                    List<Integer> indexesOfClass = findWord(xml, field.getName());
                    String subListXml = xml.substring(indexesOfClass.get(0)+ field.getName().length()+1,indexesOfClass.get(1)-2);
                    List<Integer> indexes = findWord(subListXml,className);
                    for (int i = 0; i< indexes.size();i+=2){
                        findDuplicate(subListXml.substring(indexes.get(i),indexes.get(i+1)),newClass);
                    }
                }
                else {
                    List<Integer> integerList = findWord(xml,field.getName());
                    if (integerList.size()> 2){
                        throw new DuplicatedXmlField(field.getName() +" is a duplicate");
                    }else if(integerList.isEmpty() || integerList.size() == 1){
                        throw new IncorrectXml("The given XML is not compliant with the class fields.");
                    }
                }
            }
            else {
                Object newClass = Class.forName(field.getGenericType().getTypeName()).getDeclaredConstructor().newInstance();
                findDuplicate(xml, newClass);
            }
        }
    }

    private static List<Integer> findWord( String textString, String word) {
        List<Integer> indexes = new ArrayList<>();
        String lowerCaseTextString = textString.toLowerCase();
        String lowerCaseWord = word.toLowerCase();

        int index = 0;
        while(index != -1){
            index = lowerCaseTextString.indexOf(lowerCaseWord, index);
            if (index != -1 ) {
                String afterChar = String.valueOf(lowerCaseTextString.charAt(index+ word.length()));
                String beforeChar = String.valueOf(lowerCaseTextString.charAt(index-1));
                if(afterChar.equals(">") && (beforeChar.equals("/") || beforeChar.equals("<"))) {
                    indexes.add(index);
                    index++;
                }else {
                    throw new IncorrectXml(lowerCaseWord + " field given not correct");
                }
            }
        }
        return indexes;
    }

    private static  boolean isJavaOrCustom(Class check) {
        return check.getName().startsWith("java.lang")|| check.getName().startsWith("java.util") ;
    }

}