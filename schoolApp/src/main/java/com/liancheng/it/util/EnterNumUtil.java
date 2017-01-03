package com.liancheng.it.util;

public class EnterNumUtil {
	
	public static String getEnterNum(){
		Object obj = new Object();
		String strObj = obj.toString().substring(17);

		return strObj;
	}
	
	public static String HloveyRC4(String aInput,String aKey)   
    {   
        int[] iS = new int[256];   
        byte[] iK = new byte[256];   
          
        for (int i=0;i<256;i++)   
            iS[i]=i;   
              
        int j = 1;   
          
        for (short i= 0;i<256;i++)   
        {   
            iK[i]=(byte)aKey.charAt((i % aKey.length()));   
        }   
          
        j=0;   
          
        for (int i=0;i<255;i++)   
        {   
            j=(j+iS[i]+iK[i]) % 256;   
            int temp = iS[i];   
            iS[i]=iS[j];   
            iS[j]=temp;   
        }   
      
      
        int i=0;   
        j=0;   
        char[] iInputChar = aInput.toCharArray();   
        char[] iOutputChar = new char[iInputChar.length];   
        for(short x = 0;x<iInputChar.length;x++)   
        {   
            i = (i+1) % 256;   
            j = (j+iS[i]) % 256;   
            int temp = iS[i];   
            iS[i]=iS[j];   
            iS[j]=temp;   
            int t = (iS[i]+(iS[j] % 256)) % 256;   
            int iY = iS[t];   
            char iCY = (char)iY;   
            iOutputChar[x] =(char)( iInputChar[x] ^ iCY) ;      
        }   
          
        return new String(iOutputChar);   
                  
    }  
	public static void main(String[] args) {
		getEnterNum();
//		 String inputStr = "做个好男人";      
//		    String key = "abcdefg";         
//		      
//		    String str = HloveyRC4(inputStr,key);  
//		      
//		    //打印加密后的字符串      
//		    System.out.println(str);    
//		      
//		    //打印解密后的字符串      
//		    System.out.println(HloveyRC4(str,key)); 
	}
}
