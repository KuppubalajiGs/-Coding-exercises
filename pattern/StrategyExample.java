public class StrategyExample {
    private SortStrategy strategy;
    public StrategyExample(SortStrategy strategy) { this.strategy = strategy; }
    public void setStrategy(SortStrategy s) { this.strategy = s; }
    public int[] sort(int[] arr) { return strategy.sort(arr); }

    public static void demo() {
        int[] data = {5, 2, 9, 1, 7};
        StrategyExample ctx = new StrategyExample(new BubbleSortStrategy());
        int[] r1 = ctx.sort(data);
        System.out.print("Bubble: "); for (int x : r1) System.out.print(x+" ");
        System.out.println();
        ctx.setStrategy(new QuickSortStrategy());
        int[] r2 = ctx.sort(data);
        System.out.print("Quick: "); for (int x : r2) System.out.print(x+" ");
        System.out.println();
    }
}