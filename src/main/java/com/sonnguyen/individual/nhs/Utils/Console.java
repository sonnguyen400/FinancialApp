package com.sonnguyen.individual.nhs.Utils;

import static com.sonnguyen.individual.nhs.Utils.Constants.ANSI_RED;
import static com.sonnguyen.individual.nhs.Utils.Constants.ANSI_RESET;

public class Console {
    public static void err(String string){
        System.out.println(ANSI_RED+string+ANSI_RESET);
    }

}
