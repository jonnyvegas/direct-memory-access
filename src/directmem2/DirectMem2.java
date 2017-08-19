/**
 * 
 * Name: Jonathan Villegas
 * Class: COMP222 T/TH
 * Assignment: Project 1
 * Date: 4/21/15
 * 
 * This program simulates cache memory using parameters and checking to see
 * if there is a hit or miss in the cache to read and write values.
 */

package directmem2;

import java.util.Scanner;

public class DirectMem2 
{
    //The cache to use for cache memory.
    static Cache c = new Cache();
    //The main memory.
    static int[] mainMem;
    //The memory tag.
    //Start of the word.
    static int startOfWord;
    //Address of memory.
    static int addressOfMem;
    //Size of main memory.
    static int sizeOfMain;
    //The tag to use.
    static int tag2;
    //Size of cache.
    static int cSize;
    //Size of block.
    static int bSize;
    //Start of the block.
    static int startOfBlock;

    /**
     * This method sets the parameters of the session.
     */
    public static void setParameters()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter the size of the main memory (in words): ");
        //Set the size of the main memory.
        sizeOfMain = in.nextInt();
        mainMem = new int[sizeOfMain];
        //Set the default values.
        int j = 0;
        for(int i = sizeOfMain; i > 0; i--)
        {
            
            mainMem[j] = sizeOfMain - j;
            j++;
        }
       
        //Set cache and block size.        
        System.out.print("Please enter the size of the cache (in words): ");
        cSize = in.nextInt();
        System.out.print("Please enter the size of the block (words/block): ");
        bSize = in.nextInt();
        //Block size is too small.
        if(bSize <= 0)
        {
            System.out.println("Invalid size.");
        }
        //Block size OK. Set the parameters.
        else
        {
            c.setBlockSize(cSize/bSize);
        }
    }
    
    /**
     * Reads the memory cache.
     */
    public static void readCache()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the main memory address to read from: ");
        //Read the address.
        addressOfMem = in.nextInt();
        System.out.println("The address in binary is: ");
        String theAddress = Integer.toBinaryString(addressOfMem);
        System.out.println(theAddress);
        System.out.println("The address in hexadecimal is: ");
        theAddress = Integer.toHexString(addressOfMem);
        System.out.println(theAddress);
        
        //Set the tag, word, and block.
        tag2 = addressOfMem / cSize;
        startOfWord = addressOfMem % bSize;
        startOfBlock = (addressOfMem / bSize) % (cSize / bSize);
        
        //The block is in the cache.
        if(tag2 == c.getTag())
        {
            System.out.println("Word " + startOfWord + " block " + startOfBlock
                            + " tag " + tag2 + " contains value " + mainMem[addressOfMem]);
        }
        //Not in cache. Put in cache.
        else
        {
            System.out.println("Read miss!!!");
            System.out.println("Word " + startOfWord + " block " + startOfBlock
                            + " tag " + tag2 + " contains value " + mainMem[addressOfMem]);
            c.setTag(tag2);
        }
    }
    
    /**
     * Write to the cache memory.
     */
    public static void writeCache()
    {
        Scanner in = new Scanner(System.in);
        //Get the address.
        System.out.print("Enter main memory address to be written to: ");
        int memAdd = in.nextInt();
        System.out.println("The address in binary is: ");
        String theAddress = Integer.toBinaryString(memAdd);
        System.out.println(theAddress);
        System.out.println("The address in hexadecimal is: ");
        theAddress = Integer.toHexString(memAdd);
        System.out.println(theAddress);
        
        //Get the value.
        System.out.print("Enter value to write: ");
        int theVal = in.nextInt();
        //Set tag, word, and block.
        tag2 = memAdd / cSize;
        startOfWord = memAdd % bSize;
        startOfBlock = (memAdd/bSize) % (cSize / bSize);

        //Already in cache.
        if(tag2 == c.getTag())
        {
            System.out.println("Word " + startOfWord + " block " + startOfBlock
                            + " tag " + tag2 + " contains value " + theVal);
            c.theBlock[startOfBlock] = theVal;
            mainMem[memAdd] = theVal;
        }
        //Not in cache. Write value and put block in cache.
        else
        {
            System.out.println("Write miss!!!");
            mainMem[memAdd] = theVal;
            System.out.println("Word " + startOfWord + " block " + startOfBlock
                            + " tag " + tag2 + " contains value " + theVal);
            c.setTag(tag2);
            c.theBlock[startOfBlock] = theVal;
        }
    }
    
    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        int choice = 0;
        //Set the menu.
        System.out.println("Main memory to Cache memory mapping:");
        System.out.println("====================================");
        do
        {
            //Pick an option.
            System.out.println("Please select an option: ");
            System.out.println("1. Set Parameters");
            System.out.println("2. Read cache");
            System.out.println("3. Write to cache");
            System.out.println("4. Exit");
            System.out.print("Please enter your selection: ");
            choice = in.nextInt();
            //Set the parameters.
            if(choice == 1)
            {
                setParameters();
                System.out.println();
                System.out.println("Main memory to Cache memory mapping:");
                System.out.println("====================================");
            }
            //Read the cache.
            else if(choice == 2)
            {
                readCache();
                System.out.println();
                System.out.println("Main memory to Cache memory mapping:");
                System.out.println("====================================");
            }
            //Write to cache.
            else if(choice == 3)
            {
                writeCache();
                System.out.println();
                System.out.println("Main memory to Cache memory mapping:");
                System.out.println("====================================");
            }
            //Exit.
            else if(choice == 4)
            {
                System.out.println("Exiting...");
            }
            //Not a vald choice.
            else
            {
                System.out.println("Invalid option.");
                System.out.println();
                System.out.println("Main memory to Cache memory mapping:");
                System.out.println("====================================");
                //choice = 0;
            }
        }while(choice != 4);
    }    
}