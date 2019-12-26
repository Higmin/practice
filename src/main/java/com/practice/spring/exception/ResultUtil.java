package com.practice.spring.exception;

/**
 * @author Higmin
 * @date 2019/12/26 8:32
 *
 * 全局异常处理 = > 异常处理信息设置
 **/
public class ResultUtil {

	/**返回成功,没有返回结果数据集 */
	public static Result success(){
		Result result = new Result();
		result.setCode("0");//成功
		result.setMsg("成功");//提示语
		return result;
	}

	/**返回成功,有返回结果数据集 */
	public static Result success(Object object){
		Result result = new Result();
		result.setCode("0");//成功
		result.setMsg("成功");//提示语
		result.setData(object);//返回内容
		return result;
	}

	/**返回失败 */
	public static Result fail(){
		Result result = new Result();
		result.setCode("1");//失败
		result.setMsg("失败");//提示语
		return result;
	}

	/**返回失败,自定义失败提示语 */
	public static Result fail(String msg){
		Result result = new Result();
		result.setCode("0");//失败
		result.setMsg(msg);//提示语
		return result;
	}

	/**返回失败,自定义失败code和提示语 */
	public static Result fail(String code, String msg){
		Result result = new Result();
		result.setCode(code);//失败
		result.setMsg(msg);//提示语
		return result;
	}

	public static void main(String[] args) {
		try {
			String str = null;
			System.out.println(str.equals(1));
		} catch (Exception e) {
			System.out.println(e);
			System.out.println(e.toString() + "============");
		}
	}
}
