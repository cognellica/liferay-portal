/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portalweb.demo.sitemanagement.staginglocallive;

import com.liferay.portalweb.portal.BaseTestCase;
import com.liferay.portalweb.portal.util.RuntimeVariables;

/**
 * @author Brian Wing Shun Chan
 */
public class User_SelectTopVersionNumberHistoryTest extends BaseTestCase {
	public void testUser_SelectTopVersionNumberHistory()
		throws Exception {
		selenium.selectWindow("null");
		selenium.selectFrame("relative=top");
		selenium.open("/web/guest/home/");
		selenium.waitForElementPresent("link=Site Name");
		selenium.clickAt("link=Site Name", RuntimeVariables.replace("Site Name"));
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isElementPresent(
				"//body[contains(@class,'live-view')]"));
		assertTrue(selenium.isElementNotPresent(
				"//body[contains(@class,'live-staging')]"));
		assertEquals(RuntimeVariables.replace("Staging"),
			selenium.getText("//div[@class='staging-bar']/ul/li[2]/span/a"));
		selenium.clickAt("//div[@class='staging-bar']/ul/li[2]/span/a",
			RuntimeVariables.replace("Staging"));
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isElementPresent(
				"//body[contains(@class,'local-staging')]"));
		assertTrue(selenium.isElementNotPresent(
				"//body[contains(@class,'live-view')]"));
		assertEquals(RuntimeVariables.replace("Draft"),
			selenium.getText("//span[@class='workflow-status']/strong"));
		assertEquals(RuntimeVariables.replace("History"),
			selenium.getText("//button/span[.='History']"));
		selenium.clickAt("//button/span[.='History']",
			RuntimeVariables.replace("History"));
		selenium.waitForVisible("//tr[3]/td[2]/span/span/span");
		assertEquals(RuntimeVariables.replace("Draft"),
			selenium.getText("//tr[3]/td[2]/span/span/span"));
		assertEquals(RuntimeVariables.replace("Draft"),
			selenium.getText("//tr[4]/td[2]/span/span/span"));
		assertTrue(selenium.isVisible("//tr[3]/td[3]/a"));
		selenium.clickAt("//tr[3]/td[3]/a",
			RuntimeVariables.replace("Top Version Number"));
		selenium.waitForElementPresent(
			"//div[@id='column-1']/div/div[contains(@class,'portlet-search')]");
		assertTrue(selenium.isVisible(
				"//div[@id='column-1']/div/div[contains(@class,'portlet-search')]"));
	}
}