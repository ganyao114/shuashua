package com.shuashua.buss.View.Window;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

import com.shuashua.buss.R;
import com.shuashua.buss.View.Activity.MainActivity;
import com.shuashua.buss.View.Widgets.CitySelect.Adapter.ArrayWheelAdapter;
import com.shuashua.buss.View.Widgets.CitySelect.Handler.XmlParserHandler;
import com.shuashua.buss.View.Widgets.CitySelect.IUpdateView;
import com.shuashua.buss.View.Widgets.CitySelect.Model.AreaBase;
import com.shuashua.buss.View.Widgets.CitySelect.Model.CityModel;
import com.shuashua.buss.View.Widgets.CitySelect.Model.DistrictModel;
import com.shuashua.buss.View.Widgets.CitySelect.Model.ProvinceModel;
import com.shuashua.buss.View.Widgets.CitySelect.View.OnWheelChangedListener;
import com.shuashua.buss.View.Widgets.CitySelect.View.WheelView;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SelectCityPopupWindow extends PopupWindow implements OnWheelChangedListener,OnClickListener,IUpdateView{

	public static Mode mode = Mode.Local;

	private Button confirmbtn, cancelBtn;
	private View mMenuView;

	private WheelView mViewProvince;
	private WheelView mViewCity;
	private WheelView mViewDistrict;

	protected String[] mProvinceDatas;
	protected Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
	protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();
	protected Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();
	protected String mCurrentProviceName;
	protected String mCurrentCityName;
	protected String mCurrentDistrictName ="";
	protected String mCurrentZipCode ="";



	@SuppressLint("InflateParams")
	public SelectCityPopupWindow(Context context){
		super(context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.layout_dialog_city, null);
		confirmbtn = (Button) mMenuView.findViewById(R.id.comfirm_btn);
		cancelBtn = (Button) mMenuView.findViewById(R.id.cancel_btn);

		mViewProvince = (WheelView) mMenuView.findViewById(R.id.id_province);
		mViewCity = (WheelView) mMenuView.findViewById(R.id.id_city);
		mViewDistrict = (WheelView) mMenuView.findViewById(R.id.id_district);

		cancelBtn.setOnClickListener(this);
		confirmbtn.setOnClickListener(this);

		mViewProvince.addChangingListener(this);
		mViewCity.addChangingListener(this);
		mViewDistrict.addChangingListener(this);

		this.setContentView(mMenuView);
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		this.setAnimationStyle(R.style.PopupAnimation);
		ColorDrawable dw = new ColorDrawable(0x80000000);
		this.setBackgroundDrawable(dw);
		mMenuView.setOnTouchListener(new OnTouchListener() {

			@Override
			@SuppressLint("ClickableViewAccessibility")
			public boolean onTouch(View v, MotionEvent event) {

				int height = mMenuView.findViewById(R.id.pop_layout).getTop();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height) {
						dismiss();
					}
				}
				return true;
			}
		});
		if (mode == Mode.Local)
			setUpData();
		else
			setUpDataOl();

	}

	private void setUpDataOl() {
		mViewProvince.setVisibleItems(7);
		mViewCity.setVisibleItems(7);
		mViewDistrict.setVisibleItems(7);
		updateProvinceOl(null);
		updateCityOl(null);
		updateDescOl(null);
	}


	private void setUpData() {
		initProvinceDatas();
		mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(getContentView().getContext(), mProvinceDatas));
		mViewProvince.setVisibleItems(7);
		mViewCity.setVisibleItems(7);
		mViewDistrict.setVisibleItems(7);
		updateCities();
		updateAreas();
	}

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		// TODO Auto-generated method stub
		if (wheel == mViewProvince) {
			if (mode == Mode.Local)
				updateCities();
			else
				updateCityOl(mCurrentProviceName);
		} else if (wheel == mViewCity) {
			if (mode == Mode.Local)
				updateAreas();
			else
				updateDescOl(mCurrentCityName);
		} else if (wheel == mViewDistrict) {
			if (mode == Mode.Local) {
				mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
				mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
			}else {
				mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
				mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
			}
		}
	}

	private void updateProvinceOl(String code){

	}

	private void updateCityOl(String code){

	}

	private void updateDescOl(String code){

	}

	private void updateAreas() {
		int pCurrent = mViewCity.getCurrentItem();
		mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
		String[] areas = mDistrictDatasMap.get(mCurrentCityName);

		if (areas == null) {
			areas = new String[] { "" };
		}
		mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(getContentView().getContext(), areas));
		mViewDistrict.setCurrentItem(0);
	}

	private void updateCities() {
		int pCurrent = mViewProvince.getCurrentItem();
		mCurrentProviceName = mProvinceDatas[pCurrent];
		String[] cities = mCitisDatasMap.get(mCurrentProviceName);
		if (cities == null) {
			cities = new String[] { "" };
		}
		mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(getContentView().getContext(), cities));
		mViewCity.setCurrentItem(0);
		updateAreas();
	}


	protected void initProvinceDatas()
	{
		List<ProvinceModel> provinceList = null;
		AssetManager asset = getContentView().getContext().getAssets();
		try {
			InputStream input = asset.open("province_data.xml");
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser parser = spf.newSAXParser();
			XmlParserHandler handler = new XmlParserHandler();
			parser.parse(input, handler);
			input.close();
			provinceList = handler.getDataList();
			if (provinceList!= null && !provinceList.isEmpty()) {
				mCurrentProviceName = provinceList.get(0).getName();
				List<CityModel> cityList = provinceList.get(0).getCityList();
				if (cityList!= null && !cityList.isEmpty()) {
					mCurrentCityName = cityList.get(0).getName();
					List<DistrictModel> districtList = cityList.get(0).getDistrictList();
					mCurrentDistrictName = districtList.get(0).getName();
					mCurrentZipCode = districtList.get(0).getZipcode();
				}
			}
			//*/
			mProvinceDatas = new String[provinceList.size()];
			for (int i=0; i< provinceList.size(); i++) {
				mProvinceDatas[i] = provinceList.get(i).getName();
				List<CityModel> cityList = provinceList.get(i).getCityList();
				String[] cityNames = new String[cityList.size()];
				for (int j=0; j< cityList.size(); j++) {
					cityNames[j] = cityList.get(j).getName();
					List<DistrictModel> districtList = cityList.get(j).getDistrictList();
					String[] distrinctNameArray = new String[districtList.size()];
					DistrictModel[] distrinctArray = new DistrictModel[districtList.size()];
					for (int k=0; k<districtList.size(); k++) {
						DistrictModel districtModel = new DistrictModel(districtList.get(k).getName(), districtList.get(k).getZipcode());
						mZipcodeDatasMap.put(districtList.get(k).getName(), districtList.get(k).getZipcode());
						distrinctArray[k] = districtModel;
						distrinctNameArray[k] = districtModel.getName();
					}
					mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
				}
				mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {

		}
	}

	@Override
	public void onClick(View v) {
		dismiss();
	}

	@Override
	public void onDone(Type type, List<AreaBase> areas) {
		switch (type){
			case Province:
				break;
			case City:
				break;
			case Desc:
				break;
		}
	}

	enum Mode{
		Online,
		Local
	}

	public void destory(){

	}

}
