import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Author: Nigel Smith
// Date:   09/07/16(due date)
// Tabs:   ***  (indicate the separation between tab stops)

public class Arithmetic
{
  //=================================================================
  // inc(A) returns an array of bits representing A+1.
  //=================================================================

  public static byte[] inc(byte[] A)
  {
	  int lengtha = A.length-1;
	  
	  for (int i = lengtha; i >= 0; i--)
	  {
		  if (A[i] == 0)
		  {
			  A[i] = 1;
			  for (int n = 0; n < A.length; n++){
				  System.out.print(A[n]); 
			  }
			  return A;
		  }
		  else
		  {  
			  if (A[i] == 0){
				  A[i] = 1;
				  break;
			  }
			  else {
				  A[i] = 0;
			  } 
		  }
	  }
	  for (int n = 0; n < A.length; n++){
		  System.out.print(A[n]); 
	  }
	return A;
  }

  //=================================================================
  // sum(A,B) returns an array of bits representing A+B.
  //=================================================================

  public static byte[] sum(byte[] A, byte[] B)
  {
	  int lengtha = A.length-1;
	  byte[] sumbyte;
	  int lengthb = B.length-1;
	  byte[] carry;
	  byte[] result;
	  int dif;
	  byte match[];
	  
	  if (lengtha >= lengthb)
	  {
            result = new byte[A.length];
            aGreaterEqual(lengtha, A, B);         
            return result;
	  } 
	  else
	  {
            result = new byte[B.length];
            bGreater(lengthb, A, B);
            return result;
	  }
  }
  
  //=============================================================
  //bGreater() returns the result of A+B when B is greater than A
  //=============================================================
  public static byte[] bGreater(int lengthb, byte[] A, byte[] B)
  {
      byte[] carry = new byte[lengthb+1];
	  byte[] match = new byte[B.length];
	  int dif = (B.length - A.length);
	  byte[] sumbyte = new byte[B.length];
	  byte[] result = new byte[B.length];
		  
		  for (int n = dif; n <= lengthb; n++)
		  	 {
			    match[n] = A[n - dif];
			 }
		  
		  for (int i = lengthb; i >= 0; i--)
		  {
			  if (B[i] + match[i] + carry[i] == 1){
				  sumbyte[i] = 1;
				  
				  if (i == 0)
				  {			  
					  for (int x = 0; x < sumbyte.length; x++){
						  System.out.print(sumbyte[x]); 
					  }
					  return sumbyte;  
				  }
			  }
			  else if (B[i] + match[i] + carry[i] == 2)
			  {
				  sumbyte[i] = 0;
				  
				  if (i == 0)
				  {
					  carryprint(sumbyte, result, lengthb);
					  return result;
				  } 
				  
				  carry[i-1] = 1;
			  }
			  else if (B[i] + match[i] + carry[i] == 0)
			  {
				  sumbyte[i] = 0;
			  }
			  else {
				  sumbyte[i] = 1;
				  
				  if(i==0)
				  {					 
					 if (B[i] + match[i] + carry[i] != 2)
					 {
						 carryprint(sumbyte, result, lengthb);
						 return result;
					 }					 
				  }
				  carry[i-1] = 1;			  
			  }
		  }
    return result;
  }
  
  //=========================================================================
  //aGreaterEqual() returns the result of A+B when A is greater or equal to B
  //=========================================================================
  
  public static byte[] aGreaterEqual(int lengtha, byte[] A, byte[] B)
  {	  
	  byte[] carry = new byte[lengtha+1];
	  byte[] match = new byte[A.length];
	  int dif = (A.length - B.length);
	  byte[] sumbyte = new byte[A.length];
	  byte[] result = new byte[A.length];
	  
	  for (int n = dif; n <= lengtha; n++)
	  	 {
		    match[n] = B[n - dif];
		 }
	  for (int i = lengtha; i >= 0; i--)
	  {
		  if (A[i] + match[i] + carry[i] == 1){
			  sumbyte[i] = 1;
			  
			  if (i == 0)
			  {			  
				  for (int x = 0; x < sumbyte.length; x++){
					  System.out.print(sumbyte[x]); 
				  }
				  return sumbyte;  
			  }
		  }
		  else if (A[i] + match[i] + carry[i] == 2)
		  {
			  sumbyte[i] = 0;
			  
			  if (i == 0)
			  {
				  carryprint(sumbyte, result, lengtha);
				  return result;
			  } 
			  
			  carry[i-1] = 1;
		  }
		  else if (A[i] + match[i] + carry[i] == 0)
		  {
			  sumbyte[i] = 0;
		  }
		  else {
			  sumbyte[i] = 1;
			  
			  if(i==0)
			  {					 
				 if (A[i] + match[i] + carry[i] != 2)
				 {
					 carryprint(sumbyte, result, lengtha);
					 return result;
				 }					 
			  }
			  carry[i-1] = 1;			  
		  }
	  }
	  return result;
  }
  
  
  //===========================================================================================================
  // copyprint(sumbyte, result, lengtha) returns an array of bits representing 1 added to the beginning of A+B.
  //===========================================================================================================

  public static byte[] carryprint(byte[] sumbyte, byte[] result, int length){	
	     result[0] = 1;
		 int bytelength = length; 
	  
		 for (int n = 1; n < bytelength; n++)
		 {
		     result[n] = sumbyte[n-1];
		 }
		 
		 for (int x = 0; x < result.length; x++){
			  System.out.print(result[x]); 
		  }
		 
	  return result;
  }
  
  //=================================================================
  // product(A,B) returns an array of bits representing A*B.
  //=================================================================

  public static byte[] product(byte[] A, byte[] B)
  {
	  byte[] total = new byte[(A.length+B.length)-1];
	  byte[] result;
	  int shift = total.length - A.length;
	  
	  if(A.length > B.length)
	  {	  
		  if(A[0] == 1)
		  {
 			 for (int i = B.length-1; i >= 0; --i)
			 {
				 if(B[i] == 1)
				 {   
					 byte[] aWithZero = addZero(A, shift, total.length).clone();				 
					 result = sum(aWithZero, total).clone();
					 //total = sum(total, addZero(A, shift, total.length));
				 }
				 --shift;
			}
		  }
	  }
	  
	  return null;
  }
  
  //==============================================================================================
  // addZero(A,B) returns an array of bits representing "in" inside of "size" at position "shift"
  //==============================================================================================
  
  public static byte[] addZero(byte[] in, int shift, int size)
  {
	 byte[] result = new byte[size];
	 for(int i = 0; i < in.length; i++)
	 {
		 result[i+shift] = in[i];
	 }
	 
	 for(int n = 0; n < result.length; n++)
	 {
	 System.out.print(result[n]);
	 }
	 
	 return result;
  }
  
  public static void main(String[] args)
  {
	  byte[] testa = {1,0,1,1};
	  byte[] testb = {1,1,0,1};
	  //sum(testa, testb);
	  product(testa, testb);
  } 
}
