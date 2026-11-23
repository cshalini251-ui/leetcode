class Solution:
    def checkString(self, s: str) -> bool:
      sw = 0
      for c in s:
          if sw == 0 and c == 'b':
            sw = 1
          elif sw == 1 and c == 'a':
            return False
      return True