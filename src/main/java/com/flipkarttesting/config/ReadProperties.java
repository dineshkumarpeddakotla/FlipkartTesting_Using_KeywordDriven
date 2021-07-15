/*
 *Purpose : Class is implemented for reading properties file
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 12-07-2021
 */

package com.flipkarttesting.config;

import com.flipkarttesting.utility.FileNotFoundException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

    public Properties init_properties() {

        Properties prop = new Properties();
        try {
            File file = new File(".\\src\\main\\java\\com\\flipkarttesting\\config\\config.properties");
            if (!file.exists()) {
                throw new FileNotFoundException("File doesn't exits");
            }
            FileInputStream fis = new FileInputStream(file);

            prop.load(fis);
        } catch (IOException  | FileNotFoundException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
