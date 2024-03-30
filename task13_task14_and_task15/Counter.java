// Класс счетчика
class Counter implements AutoCloseable {
    private int count;

    public Counter() {
        count = 0;
    }

    public void add() {
        count++;
    }

    @Override
    public void close() throws Exception {
        if (count == 0) {
            throw new Exception("Счетчик не использовался в блоке try-with-resources или ресурс не был закрыт");
        }
    }
}
