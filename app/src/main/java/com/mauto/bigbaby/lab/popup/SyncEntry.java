package com.mauto.bigbaby.lab.popup;

/**
 * Created by haohuidong on 19-5-30.
 */

public interface SyncEntry {

    SyncEntryOptions mOptions = new SyncEntryOptions();

    void setPriority(int priority);

    void setEntryId(String entryId);

    void setNextEntry(SyncEntryOptions nextEntry);

    String checkNextEntry();
}
