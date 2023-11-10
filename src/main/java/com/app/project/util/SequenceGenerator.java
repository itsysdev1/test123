package com.app.project.util;

import lombok.extern.log4j.Log4j2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j2
public class SequenceGenerator {

    public synchronized static long getEmpSequence(int dbSequence) throws Exception{

        log.debug("START | getEmpSequence");
        long reference;

        try{
            DateFormat df = new SimpleDateFormat("yyMMdd");
            Long date = Long.parseLong(df.format(new Date()))* 1000000;
            log.debug("(YYMMDD) Format:{}",date);

            reference= date + dbSequence;
            log.debug("EmpSequence:{}",reference);

        }catch (Exception e){
            log.debug("Error:{}",e.getMessage());
            throw new Exception(e.getMessage());
        }
        log.debug("END | getEmpSequence");
        return reference;
    }
}
