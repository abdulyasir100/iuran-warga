package com.company.mpsi.iuranwarga;
import android.app.Application;

import individual.Iuran;
import individual.Warga;

public class GlobalVariable extends Application {

    public static Warga active_warga = null;
    public static Iuran iuran = null;
    public static String iuran_ref = "";


}
