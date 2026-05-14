package com.void.repo;

import android.content.Context;
import org.json.JSONObject;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.Random;

public class FileLocker {
    private static final String MAPPING_FILE = "mapping.json";
    private static final String HIDDEN_DIR = ".data";

    private Context context;
    private String password;

    public FileLocker(Context context, String password) {
        this.context = context;
        this.password = password;
    }

    public void lockFile(File originalFile) throws Exception {
        File hiddenDir = new File(context.getFilesDir(), HIDDEN_DIR);
        if (!hiddenDir.exists()) hiddenDir.mkdirs();

        String randomName = "sys_" + System.currentTimeMillis() + "_" + new Random().nextInt(1000) + ".dat";
        File lockedFile = new File(hiddenDir, randomName);

        // Record the mapping
        JSONObject mapping = getMapping();
        mapping.put(randomName, originalFile.getAbsolutePath());
        saveMapping(mapping);

        // Move and rename
        originalFile.renameTo(lockedFile);
    }

    public void unlockAll() throws Exception {
        JSONObject mapping = getMapping();
        File hiddenDir = new File(context.getFilesDir(), HIDDEN_DIR);
        Iterator<String> keys = mapping.keys();

        while (keys.hasNext()) {
            String lockedName = keys.next();
            String originalPath = mapping.getString(lockedName);
            File lockedFile = new File(hiddenDir, lockedName);
            File originalFile = new File(originalPath);

            if (lockedFile.exists()) {
                lockedFile.renameTo(originalFile);
            }
        }

        // Clear mapping
        saveMapping(new JSONObject());
    }

    private JSONObject getMapping() throws Exception {
        File file = new File(context.getFilesDir(), MAPPING_FILE);
        if (!file.exists()) return new JSONObject();

        byte[] encoded = Files.readAllBytes(file.toPath());
        String encryptedData = new String(encoded);
        String decryptedData = CryptoUtils.decrypt(encryptedData, password);

        if (decryptedData == null) return new JSONObject();
        return new JSONObject(decryptedData);
    }

    private void saveMapping(JSONObject mapping) throws Exception {
        String encryptedData = CryptoUtils.encrypt(mapping.toString(), password);
        File file = new File(context.getFilesDir(), MAPPING_FILE);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(encryptedData.getBytes());
        }
    }
}
