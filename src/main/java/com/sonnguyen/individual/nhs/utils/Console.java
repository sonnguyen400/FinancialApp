package com.sonnguyen.individual.nhs.utils;

import static com.sonnguyen.individual.nhs.utils.Constants.ANSI_RED;
import static com.sonnguyen.individual.nhs.utils.Constants.ANSI_RESET;

public class Console {
    public static void err(String string){
        System.out.println(ANSI_RED+string+ANSI_RESET);
    }

}
