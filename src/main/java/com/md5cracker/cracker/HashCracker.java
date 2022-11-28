package com.md5cracker.cracker;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCracker {

    private File dictionary;
    private String hash;

    public HashCracker() {
    }

    public void setFilePath(File getDictionary) {
        // Maybe add try?
        dictionary = getDictionary;
    }

    public void setHash(String Hash) {
        hash = Hash.toLowerCase();
    }

    public String getFilePath(){
        return dictionary.getPath();
    }

    public static String getMd5(String input) { // https://www.geeksforgeeks.org/md5-hash-in-java/
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String performCrack() {
        // read lines from dictionary and compare computed hashes to the hash we are testing
        try (BufferedReader br = new BufferedReader(new FileReader(dictionary))) {
            String line, hashed;
            while ((line = br.readLine()) != null) {
                hashed = getMd5(line);
                System.out.println(hashed);
                if (hash.equals(hashed)) {
                    System.out.println("Found: " + line);
                    return "Hash cracked! It is " + line;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        System.out.println("Not found");
        return "Not found";
    }
}
