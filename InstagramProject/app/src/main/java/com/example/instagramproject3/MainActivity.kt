package com.example.instagramproject3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var viewList = ArrayList<View>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewList.add(LayoutInflater.from(applicationContext).inflate(R.layout.fragment_profile, null))
        viewList.add(LayoutInflater.from(applicationContext).inflate(R.layout.fragment_myphoto, null))

        // 뷰페이저에 어댑터 연결
        viewPager.adapter = pagerAdatper()

        // 페이지가 이동하면 네비게이션 버튼도 이동하도록
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener(){
            override fun onPageSelected(position: Int) {
                when(position){
                    0 -> bottomNavigationView.selectedItemId = R.id.profile
                    1 -> bottomNavigationView.selectedItemId = R.id.myPhoto
                }
            }
        })

        // 탭을 클릭하면 프래그먼트가 페이지 이동하도록
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.profile -> viewPager.setCurrentItem(0)
                R.id.myPhoto -> viewPager.setCurrentItem(1)
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    // 이너클래스로 어댑터 생성성
    inner class pagerAdatper : PagerAdapter() {
        override fun isViewFromObject(view: View, `object`: Any) = view == `object`

        override fun getCount() = viewList.size

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            var curView = viewList[position]
            viewPager.addView(curView)
            return curView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            viewPager.removeView(`object` as View)
        }
    }
}
