package com.ninlgde.patterns.templatemethod;

/**
 * @author: ninlgde
 * @date: 2020/4/28 17:47
 */
public abstract class AbstractInterface<T> {

    /** 执行日志记录 */
    public void recordLog(BaseRequest request) {
        System.out.println("收到业务ID[" + request.getBusinessId() + "]的请求");
    }

    /** 校验入参合法性 */
    public abstract Result<T> checkParam(BaseRequest request);

    /** 执行业务逻辑方法 */
    public abstract Result<T> execute(BaseRequest request);

    /** 接口请求模板方法，只用把这个方法对外部调用方暴露即可 */
    public final Result<T> templateMethod(BaseRequest request) {
        // 算法步骤1：记录日志
        recordLog(request);

        // 算法步骤2：校验入参合法性
        Result<T> result = checkParam(request);
        if(result.getCode().equals(ReturnCode.INVALID_PARAM)) {
            return result;
        }

        // 算法步骤3：执行业务方法
        result = execute(request);

        // 返回数据
        return result;
    }
}
