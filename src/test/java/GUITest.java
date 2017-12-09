import com.filefinder.gui.GUI;
import com.filefinder.gui.GUIUtils;
import com.filefinder.main.MainConfig;
import com.filefinder.nativehook.NativehookHelper;
import com.sun.javafx.robot.FXRobot;
import com.sun.javafx.robot.impl.BaseFXRobot;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.controlsfx.control.textfield.CustomTextField;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.util.concurrent.TimeoutException;

public class GUITest extends ApplicationTest
{
    final static Logger LOGGER = LogManager.getLogger(GUITest.class);

    @BeforeClass
    public static void setUpClass() throws Exception
    {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        ApplicationTest.launch(GUI.class);
    }

    @After
    public void afterEachTests()
    {
        interact(() -> GUI.getPrimaryStage().hide());
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
    public void ensureEscapeWorks()
    {
        interact(GUIUtils::toggleShow);

        // hide
        FXRobot robot = new BaseFXRobot(GUI.getPrimaryStage().getScene());
        robot.keyPress(KeyCode.ESCAPE);
        WaitForAsyncUtils.waitForFxEvents();

        Assert.assertFalse(GUI.getPrimaryStage().isShowing());
    }

    @Test
    public void ensureAltSpaceWorks()
    {
        // show
        NativehookHelper.keyPress(NativeKeyEvent.ALT_L_MASK, NativeKeyEvent.VC_SPACE);
        WaitForAsyncUtils.waitForFxEvents();

        Assert.assertTrue(GUI.getPrimaryStage().isShowing());

        // hide
        NativehookHelper.keyPress(NativeKeyEvent.ALT_L_MASK, NativeKeyEvent.VC_SPACE);
        WaitForAsyncUtils.waitForFxEvents();

        Assert.assertFalse(GUI.getPrimaryStage().isShowing());

        // show
        NativehookHelper.keyPress(NativeKeyEvent.ALT_L_MASK, NativeKeyEvent.VC_SPACE);
        WaitForAsyncUtils.waitForFxEvents();

        Assert.assertTrue(GUI.getPrimaryStage().isShowing());
    }

    @Test
    public void ensureFocusAfterShowingWorks()
    {
        interact(GUIUtils::toggleShow);

        CustomTextField searchBox = find("#searchBox");
        Assert.assertTrue(searchBox.isFocused());

        // hide
        interact(GUIUtils::toggleShow);

        // remove focus from search box
        interact(() -> GUI.getPrimaryStage().requestFocus());
        Assert.assertFalse(searchBox.isFocused());

        // show
        interact(GUIUtils::toggleShow);

        Assert.assertTrue(searchBox.isFocused());
    }

    @Test
    public void ensureSearchBoxWorks()
    {
        interact(GUIUtils::toggleShow);

        CustomTextField searchBox = find("#searchBox");

        write("bonjour");

        Assert.assertEquals("bonjour", searchBox.getText());
    }

    @Test
    public void ensureSearchBoxClearedAfterHiding()
    {
        interact(GUIUtils::toggleShow);

        CustomTextField searchBox = find("#searchBox");
        write("bonjour");
        Assert.assertEquals("bonjour", searchBox.getText());

        // hide
        interact(GUIUtils::toggleShow);

        Assert.assertEquals("", searchBox.getText());
    }

    @SuppressWarnings("unchecked")
    private <T extends Node> T find (final String query)
    {
        return (T) lookup(query).queryAll().iterator().next();
    }
}
