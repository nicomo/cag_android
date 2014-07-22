package fr.rouen.Cagliostro.service;

import com.google.android.vending.expansion.downloader.impl.DownloaderService;
import fr.rouen.Cagliostro.receiver.MyAlarmReceiver;

public class MyDownloaderService extends DownloaderService {
    // Votre clé publique fournie par Google Play
    public static final String BASE64_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtO0g58sPBoqZzTGD0djGJJrvEhgqwWklgCafy/0OUxH0GWupGEYHUpVC8YuDBUJyRj2gBiR6sGdtA/iHRbXbh+JxMiGPLXX/uOB7+tYsavvH+HNkKp57oX/Mga7xNsicgPtdHN1V8NzRxmY59PIikR3fC1A5wJ7N/vEyfyLop6VzZqcrZJ80JRs0hzLY091pVCrF3XWGXEzw5YR4RAm/tOaQxOUyoYNL0jM5Y0ghqy5ByDJYt744+SG5tPBe9xR/2PMDIASiTvJt760IlkJSGE57l0I+wh1Ya0k87nOtes8rVxZQyxrk0Ee7wf8MJDR3EXNcp7BU/ZBuHTeOWuGGRQIDAQAB";

    public static final byte[] SALT = new byte[] { 10, 2, -12, -1, 54, 18, -120, -12, 43, 2, -8, -4, 9, 50, -106, -107, -3, 45, -2, 84
    };

    @Override
    public String getPublicKey() {
        return BASE64_PUBLIC_KEY;
    }

    @Override
    public byte[] getSALT() {
        return SALT;
    }

    @Override
    public String getAlarmReceiverClassName() {
        return MyAlarmReceiver.class.getName();
    }
}