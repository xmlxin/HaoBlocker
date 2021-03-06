package com.haoutil.xposed.haoblocker.presenter;

import android.view.Menu;

import com.haoutil.xposed.haoblocker.model.entity.Rule;

public interface RulePresenter {
    void setListItems(int ruleType);

    void setMenuItems(Menu menu);

    void addRule();

    void addOrUpdateRuleSuccess(int position, Rule rule);

    void modifyRule(int position);

    void deleteRule(int position);

    void restoreRule();

    void importRules();

    void exportRules();

    void toggleAddButton(boolean visible);
}
