package com.example.den.lesson7.Interfaces;

import java.io.Serializable;

public interface PhotoItem extends Serializable {
    String getID();
    String getImgUrl();
    String getAuthorName();
    boolean getIsDeleted();

    // ORM
    void saveToDatabase();
    void deleteFromDatabase();
    void deleteFromDatabaseSafe();
    boolean isSavedToDatabase();
}
