package com.github.demonh3x.reveng;

import com.github.demonh3x.storage.ReadableFile;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ReadableFileTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Test
    public void GivenAFileWithAByte_ShouldBeReadable() throws IOException {
        final RandomReadable readable = createReadable(new byte[]{0x05});
        assertThat(readable.size(), is(1));
        assertThat(readable.read(0), is((byte) 0x05));
    }

    @Test
    public void GivenAFileWithTwoBytes_ShouldBeReadable() throws IOException {
        final RandomReadable readable = createReadable(new byte[]{0x05, 0x33});
        assertThat(readable.size(), is(2));
        assertThat(readable.read(0), is((byte) 0x05));
        assertThat(readable.read(1), is((byte) 0x33));
    }

    private RandomReadable createReadable(byte[] data) throws IOException {
        final File file = new File(testFolder.getRoot(), "readable");

        final FileOutputStream fos = new FileOutputStream(file);
        fos.write(data);
        fos.flush();
        fos.close();

        return new ReadableFile(file);
    }
}
