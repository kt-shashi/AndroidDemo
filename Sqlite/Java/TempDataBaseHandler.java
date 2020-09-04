package com.shashi.employeemanagementsql;

//This class stores the object reference of our db
//When we need to perform action to out db
//we call these functions
class TempDataBaseHandler {

    public static MyDbHandler myDbHandler;

    public static MyDbHandler getMyDbHandler() {
        return myDbHandler;
    }

    public static void setMyDbHandler(MyDbHandler myDbHandler) {
        TempDataBaseHandler.myDbHandler = myDbHandler;
    }

}
