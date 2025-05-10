package com.bitguard.models.aboutUs;

public class AboutUs {

    public static String aboutIntro(){
        return
                """
                This project is developed by Md. Abdullah Al Fuad and Md. Nazmul Hossain, students of
                Green University of Bangladesh, pursuing a B.Sc. in Computer Science and Engineering (CSE),
                As part of our Data Communication Lab course, we built this JavaFX application to demonstrate key error 
                detection and correction techniques.\s
                
                The implemented modules include:
                    - Hamming Code
                    - CRC (Cyclic Redundancy Check)
                    - Checksum
                    - Bit/Byte Stuffing and De-stuffing
                
                Our goal is to provide a clear and interactive understanding of these fundamental data 
                communication mechanisms.
                """;
    }

    public static String aboutNazmul(){

        return
                """
                Md. Nazmul Hossain
                ID: 223002089
                Mobile: 01743-886186
                Email: 223002089@student.green.ac.bd
                GitHub: nazmul-1117
                Mirpur 2, Dhaka 1216, Bangladesh
                """;
    }

    public static String aboutFuad(){
        return
                """
                Md. Abdullah Al Fuad
                ID: 223002080
                Mobile: 01902-780443
                Email: 223002080@student.green.ac.bd
                GitHub: Fuad2e3
                Kanchan, Rupganj, Narayanganj, Bangladesh
                """;
    }
}
