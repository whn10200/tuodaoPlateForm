package com.tuodao.bp.model.constant.depository;

public class CFN {
	/** sub bean. w=double u */
	public final static String swb = "swb";

	/** sub bean. w=double u */
	public final static String _swb = "_swb";

	/** sub bean. w=double u */
	public final static String _swb_ = "_swb_";

	/** sub bean. w=double u */
	public final static String swb_ = "swb_";

	/** element list */
	public final static String elel = "elel";

	/** element list */
	public final static String elelx = "elelx";

	/** element list x */
	public final static String _elelx = "_elelx";

	/** element list x */
	public final static String _elelx_ = "_elelx_";
	
	/** element list x */
	public final static String _sexs_ = _swb_+elelx+_swb_;

	/** element list x */
	public final static String elelx_ = "elelx_";

	/** underline */
	public final static String ul = "_";
	
	/** list size*/
	public final static String _lisize = "_lisize";

	
	public static String ses(Integer i) {
		return _swb_ + elel + i + _swb_;
	}
	
	public static String eleln(Integer i) {
		return elel + i;
	}

	/** element list number underline */
	public static String _eleln(Integer i) {
		return ul + elel + i;
	}

	/** element list number underline */
	public static String _eleln_(Integer i) {
		return ul + elel + i + ul;
	}

	/** element list number underline */
	public static String eleln_(Integer i) {
		return elel + i + ul;
	}

}
