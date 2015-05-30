package challengetask.group02.controllers;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Random;

import challengetask.group02.controllers.exceptions.BusyException;
import challengetask.group02.controllers.exceptions.CRCException;
import challengetask.group02.helpers.SimpleCache;
import net.tomp2p.dht.PeerBuilderDHT;
import net.tomp2p.dht.PeerDHT;
import net.tomp2p.p2p.PeerBuilder;
import net.tomp2p.peers.Number160;

import org.junit.BeforeClass;
import org.junit.Test;


import challengetask.group02.fsstructure.File;

public class FileContentControllerTest {
	
	private static String str = ("1234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890"
			+ "123456789012345678901234567890");
	
	private static PeerDHT[] peer;
	public static File file;
	private static byte[] arr;
	
	private static void copyStringToByteArray(String str, byte[] arr) {		
		
		for(int i = 0; i < str.length(); i++) {
			arr[i] = (byte)str.charAt(i);			
		}
	}
	
	@BeforeClass
	public static void method() {		
		
		peer = new PeerDHT[10];
		
        try {
            peer = createAndAttachPeersDHT(10, 7777);

            bootstrap(peer);      
            
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        
        
        arr = new byte[str.length()];
		copyStringToByteArray(str,arr);		
	}
	
	@Test
	public void doTests() throws BusyException {
		
		testCreateFile();
		testReadFile();		
	}
	
	public void testCreateFile() throws BusyException {
		
		Random random = new Random();
		
		IFileContentController fcc;
		fcc = new FileContentController(peer[4]);
		
		file = new File("Random Filename.txt", arr.length, new Number160(random));
		
        file.setDirtyBit(true);
        file.setModifierPeer(peer[4].peerID());
		
        
		ByteBuffer buf = ByteBuffer.allocate(arr.length);		
		buf.put(arr);
		buf.position(0);
		
		System.out.println("Peer 5 is writing "+(long)arr.length+" Bytes of data into the DHT");

		int bytesWritten = fcc.writeFile(file, buf, (long)arr.length, 0, new SimpleCache<File>(1));
		fcc.flush("", file);
		
		//relevant objects have been created
		assertNotEquals(file.getBlocks().size(), 0);
				
		//number of blocks are corresponding to filesize and blocksize		
		assertEquals(bytesWritten, arr.length);
	}
	
	
	public void testReadFile() {
	
		IFileContentController fcc;
		fcc = new FileContentController(peer[6]);
		
		long testLength = 2000;
		long testOffset = 3244;
		
		
		System.out.println("Peer 7 tries to read "+testLength+" Bytes of data, starting at offset "+testOffset);
		byte[] content = null;
		try {
			content = fcc.readFile(file, testLength, testOffset);
		} catch (CRCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(content.length, testLength);
	}
	
	
	
	
    public static PeerDHT[] createAndAttachPeersDHT(int nr, int port) throws IOException {
        
    	Random random = new Random();
    	
    	PeerDHT[] peers = new PeerDHT[nr];
        for (int i = 0; i < nr; i++) {
            if (i == 0) {
                peers[0] = new PeerBuilderDHT(new PeerBuilder(new Number160(random)).ports(port).start()).start();

            } else {
                peers[i] = new PeerBuilderDHT(new PeerBuilder(new Number160(random)).masterPeer(peers[0].peer()).start()).start();

            }
        }
        return peers;
    }	
    
    public static void bootstrap(PeerDHT[] peers) {
        //make perfect bootstrap, the regular can take a while
        for (int i = 0; i < peers.length; i++) {
            for (int j = 0; j < peers.length; j++) {
                peers[i].peerBean().peerMap().peerFound(peers[j].peerAddress(), null, null, null);

            }
            System.out.println("Bootstrapped peer " + i);

        }
    }    

}