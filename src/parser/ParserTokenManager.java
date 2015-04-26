/* ParserTokenManager.java */
/* Generated By:JavaCC: Do not edit this line. ParserTokenManager.java */
package parser;
import interpret.*;
import tree.*;
import tree.expression.*;
import tree.statement.*;

/** Token Manager. */
@SuppressWarnings("unused")public class ParserTokenManager implements ParserConstants {

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0){
   switch (pos)
   {
      case 0:
         if ((active0 & 0x100000000000000L) != 0L)
            return 4;
         if ((active0 & 0x3fff800L) != 0L)
         {
            jjmatchedKind = 32;
            return 20;
         }
         return -1;
      case 1:
         if ((active0 & 0x3ff7800L) != 0L)
         {
            jjmatchedKind = 32;
            jjmatchedPos = 1;
            return 20;
         }
         if ((active0 & 0x8000L) != 0L)
            return 20;
         return -1;
      case 2:
         if ((active0 & 0x3b76800L) != 0L)
         {
            jjmatchedKind = 32;
            jjmatchedPos = 2;
            return 20;
         }
         if ((active0 & 0x481000L) != 0L)
            return 20;
         return -1;
      case 3:
         if ((active0 & 0x3306800L) != 0L)
         {
            if (jjmatchedPos != 3)
            {
               jjmatchedKind = 32;
               jjmatchedPos = 3;
            }
            return 20;
         }
         if ((active0 & 0x870000L) != 0L)
            return 20;
         return -1;
      case 4:
         if ((active0 & 0x2004800L) != 0L)
         {
            jjmatchedKind = 32;
            jjmatchedPos = 4;
            return 20;
         }
         if ((active0 & 0x1302000L) != 0L)
            return 20;
         return -1;
      case 5:
         if ((active0 & 0x800L) != 0L)
         {
            jjmatchedKind = 32;
            jjmatchedPos = 5;
            return 20;
         }
         if ((active0 & 0x2004000L) != 0L)
            return 20;
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0){
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0(){
   switch(curChar)
   {
      case 33:
         jjmatchedKind = 42;
         return jjMoveStringLiteralDfa1_0(0x1000000000000L);
      case 37:
         return jjStopAtPos(0, 55);
      case 38:
         return jjMoveStringLiteralDfa1_0(0x4000000000000L);
      case 40:
         return jjStopAtPos(0, 35);
      case 41:
         return jjStopAtPos(0, 36);
      case 42:
         return jjStopAtPos(0, 53);
      case 43:
         return jjStopAtPos(0, 51);
      case 44:
         return jjStopAtPos(0, 38);
      case 45:
         return jjStopAtPos(0, 52);
      case 46:
         return jjStartNfaWithStates_0(0, 56, 4);
      case 47:
         jjmatchedKind = 54;
         return jjMoveStringLiteralDfa1_0(0xc0L);
      case 58:
         return jjStopAtPos(0, 44);
      case 59:
         return jjStopAtPos(0, 37);
      case 60:
         jjmatchedKind = 41;
         return jjMoveStringLiteralDfa1_0(0x400000000000L);
      case 61:
         jjmatchedKind = 39;
         return jjMoveStringLiteralDfa1_0(0x200000000000L);
      case 62:
         jjmatchedKind = 40;
         return jjMoveStringLiteralDfa1_0(0x800000000000L);
      case 63:
         return jjStopAtPos(0, 43);
      case 83:
         return jjMoveStringLiteralDfa1_0(0x4000L);
      case 98:
         return jjMoveStringLiteralDfa1_0(0x200800L);
      case 101:
         return jjMoveStringLiteralDfa1_0(0x460000L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x82000L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x9000L);
      case 114:
         return jjMoveStringLiteralDfa1_0(0x2800000L);
      case 116:
         return jjMoveStringLiteralDfa1_0(0x10000L);
      case 119:
         return jjMoveStringLiteralDfa1_0(0x1100000L);
      case 124:
         return jjMoveStringLiteralDfa1_0(0x2000000000000L);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0){
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 38:
         if ((active0 & 0x4000000000000L) != 0L)
            return jjStopAtPos(1, 50);
         break;
      case 42:
         if ((active0 & 0x80L) != 0L)
            return jjStopAtPos(1, 7);
         break;
      case 47:
         if ((active0 & 0x40L) != 0L)
            return jjStopAtPos(1, 6);
         break;
      case 61:
         if ((active0 & 0x200000000000L) != 0L)
            return jjStopAtPos(1, 45);
         else if ((active0 & 0x400000000000L) != 0L)
            return jjStopAtPos(1, 46);
         else if ((active0 & 0x800000000000L) != 0L)
            return jjStopAtPos(1, 47);
         else if ((active0 & 0x1000000000000L) != 0L)
            return jjStopAtPos(1, 48);
         break;
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x2a00000L);
      case 102:
         if ((active0 & 0x8000L) != 0L)
            return jjStartNfaWithStates_0(1, 15, 20);
         break;
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x110000L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x62000L);
      case 110:
         return jjMoveStringLiteralDfa2_0(active0, 0x401000L);
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x80800L);
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x1000000L);
      case 116:
         return jjMoveStringLiteralDfa2_0(active0, 0x4000L);
      case 124:
         if ((active0 & 0x2000000000000L) != 0L)
            return jjStopAtPos(1, 49);
         break;
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa3_0(active0, 0x800000L);
      case 100:
         if ((active0 & 0x400000L) != 0L)
            return jjStartNfaWithStates_0(2, 22, 20);
         break;
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x10000L);
      case 103:
         return jjMoveStringLiteralDfa3_0(active0, 0x200000L);
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x1100000L);
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x2800L);
      case 114:
         if ((active0 & 0x80000L) != 0L)
            return jjStartNfaWithStates_0(2, 19, 20);
         return jjMoveStringLiteralDfa3_0(active0, 0x4000L);
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x60000L);
      case 116:
         if ((active0 & 0x1000L) != 0L)
            return jjStartNfaWithStates_0(2, 12, 20);
         return jjMoveStringLiteralDfa3_0(active0, 0x2000000L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000L);
      case 100:
         if ((active0 & 0x800000L) != 0L)
            return jjStartNfaWithStates_0(3, 23, 20);
         break;
      case 101:
         if ((active0 & 0x20000L) != 0L)
         {
            jjmatchedKind = 17;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active0, 0x40000L);
      case 105:
         return jjMoveStringLiteralDfa4_0(active0, 0x204000L);
      case 108:
         return jjMoveStringLiteralDfa4_0(active0, 0x100800L);
      case 110:
         if ((active0 & 0x10000L) != 0L)
            return jjStartNfaWithStates_0(3, 16, 20);
         break;
      case 116:
         return jjMoveStringLiteralDfa4_0(active0, 0x1000000L);
      case 117:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 32:
         return jjMoveStringLiteralDfa5_0(active0, 0x40000L);
      case 101:
         if ((active0 & 0x100000L) != 0L)
            return jjStartNfaWithStates_0(4, 20, 20);
         else if ((active0 & 0x1000000L) != 0L)
            return jjStartNfaWithStates_0(4, 24, 20);
         return jjMoveStringLiteralDfa5_0(active0, 0x800L);
      case 110:
         if ((active0 & 0x200000L) != 0L)
            return jjStartNfaWithStates_0(4, 21, 20);
         return jjMoveStringLiteralDfa5_0(active0, 0x4000L);
      case 114:
         return jjMoveStringLiteralDfa5_0(active0, 0x2000000L);
      case 116:
         if ((active0 & 0x2000L) != 0L)
            return jjStartNfaWithStates_0(4, 13, 20);
         break;
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa6_0(active0, 0x800L);
      case 103:
         if ((active0 & 0x4000L) != 0L)
            return jjStartNfaWithStates_0(5, 14, 20);
         break;
      case 105:
         return jjMoveStringLiteralDfa6_0(active0, 0x40000L);
      case 110:
         if ((active0 & 0x2000000L) != 0L)
            return jjStartNfaWithStates_0(5, 25, 20);
         break;
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 102:
         if ((active0 & 0x40000L) != 0L)
            return jjStopAtPos(6, 18);
         break;
      case 110:
         if ((active0 & 0x800L) != 0L)
            return jjStartNfaWithStates_0(6, 11, 20);
         break;
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0xfffffffffffffffeL, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec2 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 32;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x3ff000000000000L & l) != 0L)
                     { jjCheckNAddStates(0, 3); }
                  else if (curChar == 36)
                  {
                     if (kind > 32)
                        kind = 32;
                     { jjCheckNAdd(20); }
                  }
                  else if (curChar == 34)
                     { jjCheckNAddTwoStates(9, 10); }
                  else if (curChar == 46)
                     { jjCheckNAdd(4); }
                  if ((0x3fe000000000000L & l) != 0L)
                  {
                     if (kind > 26)
                        kind = 26;
                     { jjCheckNAdd(2); }
                  }
                  else if (curChar == 48)
                  {
                     if (kind > 26)
                        kind = 26;
                  }
                  break;
               case 1:
                  if ((0x3fe000000000000L & l) == 0L)
                     break;
                  if (kind > 26)
                     kind = 26;
                  { jjCheckNAdd(2); }
                  break;
               case 2:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 26)
                     kind = 26;
                  { jjCheckNAdd(2); }
                  break;
               case 3:
                  if (curChar == 46)
                     { jjCheckNAdd(4); }
                  break;
               case 4:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 28)
                     kind = 28;
                  { jjCheckNAddTwoStates(4, 5); }
                  break;
               case 6:
                  if ((0x280000000000L & l) != 0L)
                     { jjCheckNAdd(7); }
                  break;
               case 7:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 28)
                     kind = 28;
                  { jjCheckNAdd(7); }
                  break;
               case 8:
                  if (curChar == 34)
                     { jjCheckNAddTwoStates(9, 10); }
                  break;
               case 9:
                  if ((0xfffffffbffffdbffL & l) != 0L)
                     { jjCheckNAddTwoStates(9, 10); }
                  break;
               case 10:
                  if (curChar == 34 && kind > 30)
                     kind = 30;
                  break;
               case 19:
                  if (curChar != 36)
                     break;
                  if (kind > 32)
                     kind = 32;
                  { jjCheckNAdd(20); }
                  break;
               case 20:
                  if ((0x3ff001000000000L & l) == 0L)
                     break;
                  if (kind > 32)
                     kind = 32;
                  { jjCheckNAdd(20); }
                  break;
               case 21:
                  if ((0x3ff000000000000L & l) != 0L)
                     { jjCheckNAddStates(0, 3); }
                  break;
               case 22:
                  if ((0x3ff000000000000L & l) != 0L)
                     { jjCheckNAddTwoStates(22, 23); }
                  break;
               case 23:
                  if (curChar != 46)
                     break;
                  if (kind > 28)
                     kind = 28;
                  { jjCheckNAddTwoStates(24, 25); }
                  break;
               case 24:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 28)
                     kind = 28;
                  { jjCheckNAddTwoStates(24, 25); }
                  break;
               case 26:
                  if ((0x280000000000L & l) != 0L)
                     { jjCheckNAdd(27); }
                  break;
               case 27:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 28)
                     kind = 28;
                  { jjCheckNAdd(27); }
                  break;
               case 28:
                  if ((0x3ff000000000000L & l) != 0L)
                     { jjCheckNAddTwoStates(28, 29); }
                  break;
               case 30:
                  if ((0x280000000000L & l) != 0L)
                     { jjCheckNAdd(31); }
                  break;
               case 31:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 28)
                     kind = 28;
                  { jjCheckNAdd(31); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 32)
                        kind = 32;
                     { jjCheckNAdd(20); }
                  }
                  if (curChar == 70)
                     jjstateSet[jjnewStateCnt++] = 17;
                  else if (curChar == 84)
                     jjstateSet[jjnewStateCnt++] = 13;
                  break;
               case 5:
                  if ((0x2000000020L & l) != 0L)
                     { jjAddStates(4, 5); }
                  break;
               case 9:
                  { jjAddStates(6, 7); }
                  break;
               case 11:
                  if (curChar == 101 && kind > 31)
                     kind = 31;
                  break;
               case 12:
                  if (curChar == 117)
                     { jjCheckNAdd(11); }
                  break;
               case 13:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 12;
                  break;
               case 14:
                  if (curChar == 84)
                     jjstateSet[jjnewStateCnt++] = 13;
                  break;
               case 15:
                  if (curChar == 115)
                     { jjCheckNAdd(11); }
                  break;
               case 16:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 15;
                  break;
               case 17:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 16;
                  break;
               case 18:
                  if (curChar == 70)
                     jjstateSet[jjnewStateCnt++] = 17;
                  break;
               case 19:
               case 20:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 32)
                     kind = 32;
                  { jjCheckNAdd(20); }
                  break;
               case 25:
                  if ((0x2000000020L & l) != 0L)
                     { jjAddStates(8, 9); }
                  break;
               case 29:
                  if ((0x2000000020L & l) != 0L)
                     { jjAddStates(10, 11); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 9:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     { jjAddStates(6, 7); }
                  break;
               default : if (i1 == 0 || l1 == 0 || i2 == 0 ||  l2 == 0) break; else break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 32 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private int jjMoveStringLiteralDfa0_2(){
   switch(curChar)
   {
      case 42:
         return jjMoveStringLiteralDfa1_2(0x200L);
      default :
         return 1;
   }
}
private int jjMoveStringLiteralDfa1_2(long active0){
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 1;
   }
   switch(curChar)
   {
      case 47:
         if ((active0 & 0x200L) != 0L)
            return jjStopAtPos(1, 9);
         break;
      default :
         return 2;
   }
   return 2;
}
private int jjMoveStringLiteralDfa0_1()
{
   return jjMoveNfa_1(0, 0);
}
private int jjMoveNfa_1(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 3;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x2400L & l) != 0L)
                  {
                     if (kind > 8)
                        kind = 8;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 1:
                  if (curChar == 10 && kind > 8)
                     kind = 8;
                  break;
               case 2:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : if (i1 == 0 || l1 == 0 || i2 == 0 ||  l2 == 0) break; else break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 3 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   22, 23, 28, 29, 6, 7, 9, 10, 26, 27, 30, 31, 
};
private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec2[i2] & l2) != 0L);
      default :
         if ((jjbitVec0[i1] & l1) != 0L)
            return true;
         return false;
   }
}

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, null, null, 
"\142\157\157\154\145\141\156", "\151\156\164", "\146\154\157\141\164", "\123\164\162\151\156\147", 
"\151\146", "\164\150\145\156", "\145\154\163\145", "\145\154\163\145\40\151\146", 
"\146\157\162", "\167\150\151\154\145", "\142\145\147\151\156", "\145\156\144", 
"\162\145\141\144", "\167\162\151\164\145", "\162\145\164\165\162\156", null, null, null, null, 
null, null, null, null, null, "\50", "\51", "\73", "\54", "\75", "\76", "\74", 
"\41", "\77", "\72", "\75\75", "\74\75", "\76\75", "\41\75", "\174\174", "\46\46", 
"\53", "\55", "\52", "\57", "\45", "\56", };
protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token specialToken = null;
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      jjmatchedPos = -1;
      matchedToken = jjFillToken();
      matchedToken.specialToken = specialToken;
      return matchedToken;
   }
   image = jjimage;
   image.setLength(0);
   jjimageLen = 0;

   for (;;)
   {
     switch(curLexState)
     {
       case 0:
         try { input_stream.backup(0);
            while (curChar <= 32 && (0x100003600L & (1L << curChar)) != 0L)
               curChar = input_stream.BeginToken();
         }
         catch (java.io.IOException e1) { continue EOFLoop; }
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_0();
         break;
       case 1:
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_1();
         if (jjmatchedPos == 0 && jjmatchedKind > 10)
         {
            jjmatchedKind = 10;
         }
         break;
       case 2:
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_2();
         if (jjmatchedPos == 0 && jjmatchedKind > 10)
         {
            jjmatchedKind = 10;
         }
         break;
     }
     if (jjmatchedKind != 0x7fffffff)
     {
        if (jjmatchedPos + 1 < curPos)
           input_stream.backup(curPos - jjmatchedPos - 1);
        if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
           matchedToken = jjFillToken();
           matchedToken.specialToken = specialToken;
       if (jjnewLexState[jjmatchedKind] != -1)
         curLexState = jjnewLexState[jjmatchedKind];
           return matchedToken;
        }
        else if ((jjtoSkip[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
           if ((jjtoSpecial[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
           {
              matchedToken = jjFillToken();
              if (specialToken == null)
                 specialToken = matchedToken;
              else
              {
                 matchedToken.specialToken = specialToken;
                 specialToken = (specialToken.next = matchedToken);
              }
              SkipLexicalActions(matchedToken);
           }
           else
              SkipLexicalActions(null);
         if (jjnewLexState[jjmatchedKind] != -1)
           curLexState = jjnewLexState[jjmatchedKind];
           continue EOFLoop;
        }
        jjimageLen += jjmatchedPos + 1;
      if (jjnewLexState[jjmatchedKind] != -1)
        curLexState = jjnewLexState[jjmatchedKind];
        curPos = 0;
        jjmatchedKind = 0x7fffffff;
        try {
           curChar = input_stream.readChar();
           continue;
        }
        catch (java.io.IOException e1) { }
     }
     int error_line = input_stream.getEndLine();
     int error_column = input_stream.getEndColumn();
     String error_after = null;
     boolean EOFSeen = false;
     try { input_stream.readChar(); input_stream.backup(1); }
     catch (java.io.IOException e1) {
        EOFSeen = true;
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
        if (curChar == '\n' || curChar == '\r') {
           error_line++;
           error_column = 0;
        }
        else
           error_column++;
     }
     if (!EOFSeen) {
        input_stream.backup(1);
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
     }
     throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
   }
  }
}

void SkipLexicalActions(Token matchedToken)
{
   switch(jjmatchedKind)
   {
      default :
         break;
   }
}
private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

    /** Constructor. */
    public ParserTokenManager(JavaCharStream stream){

      if (JavaCharStream.staticFlag)
            throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");

    input_stream = stream;
  }

  /** Constructor. */
  public ParserTokenManager (JavaCharStream stream, int lexState){
    ReInit(stream);
    SwitchTo(lexState);
  }

  /** Reinitialise parser. */
  public void ReInit(JavaCharStream stream)
  {
    jjmatchedPos = jjnewStateCnt = 0;
    curLexState = defaultLexState;
    input_stream = stream;
    ReInitRounds();
  }

  private void ReInitRounds()
  {
    int i;
    jjround = 0x80000001;
    for (i = 32; i-- > 0;)
      jjrounds[i] = 0x80000000;
  }

  /** Reinitialise parser. */
  public void ReInit(JavaCharStream stream, int lexState)
  {
    ReInit(stream);
    SwitchTo(lexState);
  }

  /** Switch to specified lex state. */
  public void SwitchTo(int lexState)
  {
    if (lexState >= 3 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
    else
      curLexState = lexState;
  }

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
   "IN_SINGLE_LINE_COMMENT",
   "IN_MULTI_LINE_COMMENT",
};

/** Lex State array. */
public static final int[] jjnewLexState = {
   -1, -1, -1, -1, -1, -1, 1, 2, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, 
};
static final long[] jjtoToken = {
   0x1fffff9d7fff801L, 
};
static final long[] jjtoSkip = {
   0x33eL, 
};
static final long[] jjtoSpecial = {
   0x300L, 
};
static final long[] jjtoMore = {
   0x4c0L, 
};
    protected JavaCharStream  input_stream;

    private final int[] jjrounds = new int[32];
    private final int[] jjstateSet = new int[2 * 32];

    private final StringBuilder jjimage = new StringBuilder();
    private StringBuilder image = jjimage;
    private int jjimageLen;
    private int lengthOfMatch;
    
    protected char curChar;
}
