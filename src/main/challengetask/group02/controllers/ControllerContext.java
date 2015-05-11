package challengetask.group02.controllers;

import challengetask.group02.fsstructure.Entry;
import challengetask.group02.fsstructure.File;
import net.fusejna.StructStat;
import net.tomp2p.dht.PeerDHT;
import net.tomp2p.peers.Number160;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * The FS operations handled by the controller are implemented in the strategy pattern. See github wiki for explanation:
 * https://github.com/lisgie/ChallengeTask_Group2/wiki/Architecture
 */
public class ControllerContext {

    private final FileContentController fileContentController;
    private final TreeControllerStrategy treeController;


    public ControllerContext(PeerDHT peer) {
        this.treeController = new TreeController(peer);
        this.fileContentController = new FileContentController(peer);
    }

    public Entry getEntryFromID(Number160 ID) throws IOException, ClassNotFoundException {
        return treeController.getEntryFromID(ID);
    }

    public ArrayList<String> readDir(String path) throws IOException, ClassNotFoundException, NotADirectoryException, NoSuchFileOrDirectoryException {
        return treeController.readDir(path);
    }

    public Entry findEntry(String path) throws IOException, ClassNotFoundException, NotADirectoryException, NoSuchFileOrDirectoryException {
        return treeController.findEntry(path);
    }

    public void createDir(String path) throws ClassNotFoundException, NotADirectoryException, IOException, NoSuchFileOrDirectoryException {
        treeController.createDir(path);
    }

    public void createFile(String path) throws ClassNotFoundException, NotADirectoryException, IOException, NoSuchFileOrDirectoryException {
        this.treeController.createFile(path);
    }

    public void rename(String path, String newName) throws ClassNotFoundException, NotADirectoryException, IOException, NoSuchFileOrDirectoryException {
        treeController.renameEntry(path, newName);
    }

    public void deleteDirectory(String path) throws ClassNotFoundException, NotADirectoryException, NoSuchFileOrDirectoryException, IOException, DirectoryNotEmptyException {
        treeController.removeDirectory(path);
    }

    /**
     * This method first clears the file (deletes all blocks) and then unlinks and deletes the file object.
     * @param path
     * @throws ClassNotFoundException
     * @throws NotADirectoryException
     * @throws NoSuchFileOrDirectoryException
     * @throws NotAFileException
     * @throws IOException
     */
    public void deleteFile(String path) throws ClassNotFoundException, NotADirectoryException, NoSuchFileOrDirectoryException, NotAFileException, IOException {
        treeController.deleteFile(path);
    }

    public byte[] readFile(String path, long size, long offset) throws ClassNotFoundException, NotADirectoryException, NotAFileException, IOException, NoSuchFileOrDirectoryException {
        File file = treeController.getFile(path);
        return this.fileContentController.readFile(file, size, offset);
    }

    public int writeFile(String path, ByteBuffer buf, long bufSize, long writeOffset) throws ClassNotFoundException, NotADirectoryException, NotAFileException, IOException, NoSuchFileOrDirectoryException, BusyException {
        File file = treeController.getFile(path);
        return this.fileContentController.writeFile(file, buf, bufSize, writeOffset);
    }

    //used for the locking logic
	public void whenFileClosed(String path) {
		treeController.whenFileClosed(path);		
	}
	
	public void updateFileMetaData(Entry entry, final StructStat.StatWrapper stat) {
		treeController.updateFileMetaData(entry, stat);		
	}

    //TODO test creating a root directory object "/" and some other directories

    //TODO moving the other directories into the root directory

    //TODO test listing the content of root "/" and assert if added directories are in it

    //TODO test requesting a certain subdirectory of root "/"

    //TODO test what happens if a nonexisting subdirectory is requested
}
