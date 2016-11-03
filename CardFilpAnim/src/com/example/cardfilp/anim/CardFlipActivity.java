package com.example.cardfilp.anim;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ʹ���Զ���Fragment����ʵ��Card��ת������
 * 
 * Card��ת����ͨ��ģ��Card��ת��Ч��ʵ��view���ݵ��л�
 * 
 * 
 * Ҫ��
 * 
 * һ���������card���Ҳ�����ת������һ���ñ����Card���ҷ�ת���롣���ǻ���Ҫ����
 * Animator�ñ����card���Ҳ�����ת���룬һ�������ҷ�ת���롣
 * 
 * ����:
 * 
 * 1����Fragmentת���������Ǹ������Զ��嶯��
 * 
 * 2������Fragment�滻��ǰ��ʾ��Fragment������Ӧ��֮ǰ�����Ķ��������¼��С�
 * 
 * 3�����֮ǰ��ʾ��Fragment��Fragment�Ļ���ջ��back stack���У����Ե��û����� Back ��ʱ��Card�ᷭת������
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

		// Ĭ����ʾ�����Fragment
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
				// ��Fragmentת���������Ǹ������Զ��嶯��
				.setCustomAnimations(R.animator.card_flip_right_in,
						R.animator.card_flip_right_out,
						R.animator.card_flip_left_in,
						R.animator.card_flip_left_out)
				// ����Fragment�滻��ǰ��ʾ��Fragment������Ӧ��֮ǰ�����Ķ��������¼���
				.replace(R.id.container, new CardBackFragment())
				// ���֮ǰ��ʾ��Fragment��Fragment�Ļ���ջ��back stack����
				.addToBackStack(null).commit();

	}

	// ����
	class CardFrontFragment extends Fragment {
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			return inflater.inflate(R.layout.fragment_card_front, container,
					false);
		}
	}

	// ����
	class CardBackFragment extends Fragment {
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			return inflater.inflate(R.layout.fragment_card_back, container,
					false);
		}
	}
}
