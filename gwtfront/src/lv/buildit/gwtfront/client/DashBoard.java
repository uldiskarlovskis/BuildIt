/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package lv.buildit.gwtfront.client;

import lv.buildit.gwtfront.widgets.DataLabel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.i18n.client.HasDirection.Direction;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DashBoard implements EntryPoint {
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();
		rootPanel.setSize("", "");
		
		VerticalPanel verticalPanel = new VerticalPanel();
		rootPanel.add(verticalPanel, 5, 5);
		verticalPanel.setSize("800px", "");
		
		HorizontalPanel topGlobalPanel = new HorizontalPanel();
		topGlobalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.add(topGlobalPanel);
		verticalPanel.setCellHorizontalAlignment(topGlobalPanel, HasHorizontalAlignment.ALIGN_CENTER);
		
		InlineLabel topWelcomeText = new InlineLabel("Welcome Uldis");
		topGlobalPanel.add(topWelcomeText);
		
		Hyperlink hprlnkDashboard = new Hyperlink("Dashboard", false, "newHistoryToken");
		topGlobalPanel.add(hprlnkDashboard);
		
		Hyperlink hprlnkAdministration = new Hyperlink("Administration", false, "newHistoryToken");
		topGlobalPanel.add(hprlnkAdministration);
		
		Hyperlink hprlnkProfile = new Hyperlink("Profile", false, "newHistoryToken");
		topGlobalPanel.add(hprlnkProfile);
		
		Hyperlink hprlnkSystem = new Hyperlink("System", false, "newHistoryToken");
		topGlobalPanel.add(hprlnkSystem);
		
		HorizontalPanel infoPanel = new HorizontalPanel();
		verticalPanel.add(infoPanel);
		infoPanel.setHeight("");
		
		InlineLabel nlnlblNewInlinelabel = new InlineLabel("View bla bal");
		infoPanel.add(nlnlblNewInlinelabel);
		
		SplitLayoutPanel splitLayoutPanel = new SplitLayoutPanel();
		verticalPanel.add(splitLayoutPanel);
		splitLayoutPanel.setSize("800px", "400px");
		
		SimplePanel simplePanel = new SimplePanel();
		splitLayoutPanel.addWest(simplePanel, 150.0);
		
		Tree toolSet = new Tree();
		toolSet.setAnimationEnabled(true);
		ScrollPanel treeWrapper = new ScrollPanel(toolSet);
		simplePanel.setWidget(treeWrapper);
		treeWrapper.setSize("100%", "100%");
		
		TreeItem trtmNewItem = new TreeItem("New item");
		trtmNewItem.setText("Group1");
		toolSet.addItem(trtmNewItem);
		
		
		TreeItem treeItem_2 = new TreeItem("New item");
		treeItem_2.setText("Group2");
		trtmNewItem.addItem(treeItem_2);
		
		
		createTreeItem(trtmNewItem, "step 1");
		createTreeItem(trtmNewItem, "step 2");
		
		
		final AbsolutePanel designBoard = new AbsolutePanel();
		splitLayoutPanel.add(designBoard);
		
		//DragOverHandler is required!!!
		designBoard.addDomHandler(new DragOverHandler() {
			@Override
			public void onDragOver(DragOverEvent event) {
				// TODO Auto-generated method stub
			}
		}, DragOverEvent.getType());

				
		designBoard.addDomHandler(new DropHandler() {
			
			@Override
			public void onDrop(DropEvent event) {		
				String data = event.getData("text");
				//TODO: if existed on the same panel, don't make a new object
				//can be implemented with aggregation actually
				DataLabel item = new DataLabel(data);
				makeDraggable(item);
				int xPos = event.getNativeEvent().getClientX() - designBoard.getAbsoluteLeft();
				int yPos = event.getNativeEvent().getClientY() - designBoard.getAbsoluteTop();
				designBoard.add(item, xPos, yPos);
			}
		}, DropEvent.getType());
		
		
		HorizontalPanel footerPanel = new HorizontalPanel();
		verticalPanel.add(footerPanel);
		
		InlineLabel nlnlblFooter = new InlineLabel("Footer");
		footerPanel.add(nlnlblFooter);
	}
	
	private void createTreeItem(TreeItem parentTreeItem, final String id){
		DataLabel item = new DataLabel();
		item.setText(id);
		
		parentTreeItem.addItem(item);
		makeDraggable(item);
	}
	
	//todo: must be generic
	private void makeDraggable(final DataLabel item){
		item.getElement().setDraggable(Element.DRAGGABLE_TRUE);
		item.addDragStartHandler(new DragStartHandler() {
			@Override
			public void onDragStart(DragStartEvent event) {
				event.setData("text", item.getText());
			}
		});
	}
	
}
