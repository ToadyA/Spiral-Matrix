public class Algorithm {
    public int edges(int m, int n, int i) {
        int result = 0;
        for (int j = 0; j < i; j++) {
            if(j % 2 == 0)
                result += m - (j / 2);
            else
                result += n - ((j + 1) / 2);
        }
        if(result > 0)
            result -= 1;
        return (result);
    }
}
