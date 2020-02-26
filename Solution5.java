package code;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Varadharajan on 26/02/20 02:25
 * @Projectname untitled
 */
public class Solution5
{
    public static void main(String[] args)
    {
        int U = 2;
        int L = 3;
        int C[] = {0,0,1,1,2};

        List<List<Integer>> listOfLists = (checkForPossibleMatrixFormation(U,L,C));
        String str = getValuesPresent(listOfLists);
        System.out.println(str);

    }
    private static String getValuesPresent(List<List<Integer>> listOfLists) {
        if(listOfLists.isEmpty())
           return "IMPOSSIBLE";
        else {
            StringBuilder sb = new StringBuilder();
            listOfLists.stream().forEach(list -> {
                String joinedList = list.stream().map(String::valueOf).collect(Collectors.joining(""));
                sb.append(joinedList);
                sb.append(",");
            });
            sb.setLength(sb.length() - 1);
            return sb.toString();
        }
    }
    public static List<List<Integer>> checkForPossibleMatrixFormation(int U, int L, int[] C) {
        List<List<Integer>> twoDMatrixContainsPossibleValues = new ArrayList();
        List<Integer> topWardsValidator = new ArrayList();
        List<Integer> bottomWardsValidator = new ArrayList();
        for (int num : C) {
            if (num == 0) {
                topWardsValidator.add(0);
                bottomWardsValidator.add(0);
            } else if (num == 2) {
                topWardsValidator.add(1);
                bottomWardsValidator.add(1);
                U--;
                L--;
            } else if (U == 0 && L == 0) {
                return twoDMatrixContainsPossibleValues;
            } else if (U > L) {
                topWardsValidator.add(1);
                bottomWardsValidator.add(0);
                U--;
            } else {
                topWardsValidator.add(0);
                bottomWardsValidator.add(1);
                L--;
            }
        }
        if (U != 0 || L != 0)
            return new ArrayList<>();
        twoDMatrixContainsPossibleValues.add(topWardsValidator);
        twoDMatrixContainsPossibleValues.add(bottomWardsValidator);
        return twoDMatrixContainsPossibleValues;
    }
}
