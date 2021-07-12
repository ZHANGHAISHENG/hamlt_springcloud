package com.hamlt.demo.zk;

public interface UidGenerator {
    /**
     * Get a unique ID
     *
     * @return UID
     */
    long getUID() ;

    /**
     * Parse the UID into elements which are used to generate the UID. <br>
     * Such as timestamp & workerId & sequence...
     *
     * @return Parsed info
     */
    String parseUID();
}
