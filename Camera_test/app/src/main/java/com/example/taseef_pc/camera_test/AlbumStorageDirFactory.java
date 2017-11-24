package com.example.taseef_pc.camera_test;

import java.io.File;

/**
 * Created by Taseef-PC on 8/6/2016.
 */
abstract class AlbumStorageDirFactory {
    public abstract File getAlbumStorageDir(String album_name);
}