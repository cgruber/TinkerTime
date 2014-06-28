package test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import aohara.tinkertime.controllers.ModDownloadManager;
import aohara.tinkertime.controllers.ModManager;
import aohara.tinkertime.controllers.ModUpdateListener;
import aohara.tinkertime.models.Mod;
import aohara.tinkertime.models.ModPage;

public class TestModManager {
	
	private ModDownloadManager dm;
	private ModUpdateListener ul;
	
	@Before
	public void setUp(){
		dm = mock(ModDownloadManager.class);
		ul = mock(ModUpdateListener.class);
	}

	@Test
	public void testAddMod() {
		final ModPage page = UnitTestSuite.getModPage(
			"Kerbal Engineer Redux",
			"http://www.curse.com/ksp-mods/kerbal/220285-kerbal-engineer-redux"
		);
		Mod mod = ModManager.addNewMod(page, dm, ul);
	
		verify(dm, times(1)).downloadMod(mod);
	}

}