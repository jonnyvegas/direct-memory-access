/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package directmem2;

/**
 * Class used as a cache for memory.
 */
public class Cache 
{
    public int theTag;
    public int[] theBlock;
    
    public Cache()
    {
        theTag = -1;
    }
    public void setTag(int theTag)
    {
        this.theTag = theTag;
    }
    public int getTag()
    {
        return theTag;
    }
    public void setBlockSize(int theSize)
    {
        theBlock = new int[theSize];
    }
}
