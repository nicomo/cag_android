package fr.rouen.Cagliostro.provider;

import com.android.vending.expansion.zipfile.APEZProvider;

public class ZipFileContentProvider extends APEZProvider {

    @Override
    public String getAuthority() {
        return "fr.rouen.Cagliostro.provider.ZipFileContentProvider";
    }
}