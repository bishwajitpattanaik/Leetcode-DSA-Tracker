LC-11 Problem: Container With Most Water

Code:
class Solution {
    public int maxArea(int[] height) {
        //Initialize values of max, left, right 
        int max = 0;
        int left = 0;
        int right = height.length-1;

        while(left<right){
            int width = right-left; //w=r-l
            int area = Math.min(height[left],height[right])*width; //area = min(r,l)*w

            max = Math.max(max, area); //max gets updated
            
            //Shifting pointers iteration
            if(height[left] <= height[right]){
                left++;
            }
            else{
                right--;
            }
            
        }

        return max;
    }
}
