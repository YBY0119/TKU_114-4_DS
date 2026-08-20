class Result<T> {
    private final boolean success;
    private final String message;
    private final T data;

    private Result(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(true, "Success", data);
    }

    public static <T> Result<T> fail(String message) {
        return new Result<>(false, message, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Result{success=" + success + ", message='" + message + "', data=" + data + "}";
    }
}

public class GenericResultDemo {
    public static void main(String[] args) {
        // Result<String> 成功與失敗
        Result<String> strSuccess = Result.ok("Hello Java Generics");
        Result<String> strFail = Result.fail("字串獲取失敗");

        // Result<Integer> 成功與失敗
        Result<Integer> intSuccess = Result.ok(100);
        Result<Integer> intFail = Result.fail("找不到數值");

        // 取出資料不需 cast
        String text = strSuccess.getData();
        Integer number = intSuccess.getData();

        System.out.println("strSuccess: " + strSuccess + ", Value: " + text);
        System.out.println("strFail: " + strFail + ", Data is: " + strFail.getData());
        System.out.println("intSuccess: " + intSuccess + ", Value: " + number);
        System.out.println("intFail: " + intFail + ", Data is: " + intFail.getData());
    }
}