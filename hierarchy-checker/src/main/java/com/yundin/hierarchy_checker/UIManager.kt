package com.yundin.hierarchy_checker

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.View
import com.yundin.hierarchy_checker.adviser.Adviser
import com.yundin.hierarchy_checker.adviser.RandomAdviser
import com.yundin.hierarchy_checker.analyzer.ConcreteHierarchyAnalyzer
import com.yundin.hierarchy_checker.analyzer.HierarchyAnalyzer
import com.yundin.hierarchy_checker.outputter.LogOutputter
import com.yundin.hierarchy_checker.outputter.RecommendationOutputter

class UIManager {

    companion object {
        @JvmStatic
        fun init(application: Application) {

            val outputter: RecommendationOutputter = LogOutputter()
            val adviser: Adviser = RandomAdviser()
            val analyzer: HierarchyAnalyzer = ConcreteHierarchyAnalyzer(adviser, outputter)

            application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                override fun onActivityStarted(activity: Activity) {
                    analyzer.analyzeDynamicHierarchy(getActivityRoot(activity))
                }

                override fun onActivityPaused(activity: Activity) {}
                override fun onActivityDestroyed(activity: Activity) {}
                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
                override fun onActivityStopped(activity: Activity) {}
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
                override fun onActivityResumed(activity: Activity) {}
            })
        }


        private fun getActivityRoot(activity: Activity): View {
            return activity.window.decorView.rootView
        }
    }
}