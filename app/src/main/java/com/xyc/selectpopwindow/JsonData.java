package com.xyc.selectpopwindow;

import com.google.gson.Gson;

/**
 * Created by hasee on 2018/4/4.
 */

public class JsonData {
    private static String jsonDatas = "{\n" +
            "              \"firstLevel\":[\n" +
            "                    {\n" +
            "                      \"name\":\"name-1\",\n" +
            "                       \"id\":12,\n" +
            "                       \"secondLevel\":[\n" +
            "                              {\n" +
            "                               \"name\":\"name-1-1\",\n" +
            "                                 \"id\":100,\n" +
            "                               \"thirdLevel\":[\n" +
            "                                            {\n" +
            "                                                \"name\":\"name-1-1-1\" ,\n" +
            "                                                    \"id\":50\n" +
            "                                             }, {\n" +
            "                                                \"name\":\"name-1-1-2\" ,\n" +
            "                                                   \"id\":51\n" +
            "                                             }\n" +
            "                               \n" +
            "                                    ]\n" +
            "                              \n" +
            "                                 },\n" +
            "    {\n" +
            "                               \"name\":\"name-1-2\",\n" +
            "                                     \"id\":101,\n" +
            "                               \"thirdLevel\":[\n" +
            "                                            {\n" +
            "                                                \"name\":\"name-1-2-1\" \n" +
            "                                                       , \"id\":52\n" +
            "                                             },  \n" +
            "                                            {\n" +
            "                                                \"name\":\"name-1-2-2\" \n" +
            "                                                       , \"id\":53\n" +
            "                                             }\n" +
            "                               \n" +
            "                                    ]\n" +
            "                              \n" +
            "                                 }  \n" +
            "                              ]\n" +
            "                         },\n" +
            "     {\n" +
            "                      \"name\":\"name-2\",\n" +
            "                           \"id\":13,\n" +
            "                       \"secondLevel\":[\n" +
            "                              {\n" +
            "                               \"name\":\"name-2-1\",\n" +
            "                           \"id\":19,\n" +
            "                               \"thirdLevel\":[\n" +
            "                                            {\n" +
            "                                                \"name\":\"name3\" \n" +
            "                                                       , \"id\":199\n" +
            "                                             }\n" +
            "                               \n" +
            "                                    ]\n" +
            "                              \n" +
            "                                 }  \n" +
            "                              ]\n" +
            "                         }\n" +
            "               ]\n" +
            "    \n" +
            "}";

    public static NetDataModel getNetData() {
        Gson gson = new Gson();
        NetDataModel netDataModel = gson.fromJson(jsonDatas, NetDataModel.class);
        return netDataModel;
    }
}
