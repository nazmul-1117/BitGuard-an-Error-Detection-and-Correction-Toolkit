package com.bitguard.models.manual;

public class Manual {

    public static String hammingCode(){

        return
                """
                                 Hamming Code              
                                            
                                            
                Step 1: Give The Actual Data
                Step 2: Give Parity for Calculating
                Step 3: Give Method which wanna convert
                Step 4: If Send with Error then check that box
                Step 5: Give Error Index (1 based index)
                """;
    }

    public static String crc(){

        return
                """
                                 CRC Code       
                                            
                                            
                Step 1: Give The Actual Data
                Step 2: Give The Key (Divisor)
                Step 3: If Send with Error then check that box
                Step 4: Give Error Index (1 based index)
                Step 5: Click on "Send" Button
                """;
    }

    public static String checksum(){

        return
                """
                                 Checksum Code                   
                                           
                                            
                Step 1: Give The Actual Data
                Step 2: Give Segment Size (Hoe many bits in 1 Segment)
                Step 3: If Send with Error then check that box
                Step 4: Give Error Index (1 based index)
                Step 5: Click on "Send" Button
                """;
    }

    public static String stuffing(){

        return
                """
                            Stuffing/De-Stuffing Code
                Step 1: Give The Actual Data
                Step 2: Give Stuff Type (Bit/Byte)
                    Note:
                        1. Bit: When 5 Ones consecutive
                            - Push '0' After
                        2. Byte: When got 'E' or 'F'
                            - Push 'E' or 'F' Before
                Step 3: Give Method which wanna convert
                        Note:
                            1. Stuffing: It stuff the main data (Encoding)
                            2. De-Stuffing: De-Stuff the Stuffed Data (Decoding)
                Step 4: Click on "Send" Button
                """;
    }


}
