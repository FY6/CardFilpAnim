package com.example.cardfilp.anim;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 使用自定义Fragment动画实现Card翻转动画。
 * 
 * Card翻转动画通过模拟Card翻转的效果实现view内容的切换
 * 
 * 
 * 要求：
 * 
 * 一个让正面的card的右侧向左翻转渐出，一个让背面的Card向右翻转渐入。我们还需要两个
 * Animator让背面的card的右侧向左翻转渐入，一个让向右翻转渐入。
 * 
 * 步骤:
 * 
 * 1、将Fragment转换设置我们刚做的自定义动画
 * 
 * 2、用新Fragment替换当前显示的Fragment，并且应用之前创建的动画到该事件中。
 * 
 * 3、添加之前显示的Fragment到Fragment的回退栈（back stack）中，所以当用户按下 Back 键时，Card会翻转回来。
 * 
 * 
 * @author wfy
 * 
 */
public class CardFlipActivity extends Activity {

	private boolean mShowingBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_flip);

		// 默认显示正面的Fragment
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new CardFrontFragment()).commit();
		}
	}

	public void flipCard(View v) {

		if (mShowingBack) {
			getFragmentManager().popBackStack();
			return;
		}
		mShowingBack = true;
		getFragmentManager().beginTransaction()
				// 将Fragment转换设置我们刚做的自定义动画
				.setCustomAnimations(R.animator.card_flip_right_in,
						R.animator.card_flip_right_out,
						R.animator.card_flip_left_in,
						R.animator.card_flip_left_out)
				// 用新Fragment替换当前显示的Fragment，并且应用之前创建的动画到该事件中
				.replace(R.id.container, new CardBackFragment())
				// 添加之前显示的Fragment到Fragment的回退栈（back stack）中
				.addToBackStack(null).commit();

	}

	// 正面
	class CardFrontFragment extends Fragment {
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			return inflater.inflate(R.layout.fragment_card_front, container,
					false);
		}
	}

	// 反面
	class CardBackFragment extends Fragment {
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			return inflater.inflate(R.layout.fragment_card_back, container,
					false);
		}
	}
}
