import cn.hutool.core.io.IoUtil;
import com.hy.Application;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.flowable.engine.RepositoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Description:
 * Author: yhong
 * Date: 2024/1/18
 */
@SpringBootTest(classes = Application.class)
public class SvgToPngTest {

    @Autowired
    private RepositoryService repositoryService;
    @Test
    public void test() {
        long startTime = System.nanoTime();

        try {
            String svgXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<!-- created with bpmn-js / http://bpmn.io -->\n" +
                    "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n" +
                    "<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" width=\"395\" height=\"92\" viewBox=\"-251 94 395 92\" version=\"1.1\"><defs><marker id=\"sequenceflow-end-white-black-e44tqyj3lmgalz7vnlb1g4ihr\" viewBox=\"0 0 20 20\" refX=\"11\" refY=\"10\" markerWidth=\"10\" markerHeight=\"10\" orient=\"auto\"><path d=\"M 1 5 L 11 10 L 1 15 Z\" style=\"fill: black; stroke-width: 1px; stroke-linecap: round; stroke-dasharray: 10000, 1; stroke: black;\"/></marker></defs><g class=\"djs-group\"><g class=\"djs-element djs-connection\" data-element-id=\"Flow_1voplw0\" style=\"display: block;\"><g class=\"djs-visual\"><path d=\"m  -215,140L-110,140 \" style=\"fill: none; stroke-width: 2px; stroke: black; stroke-linejoin: round; marker-end: url('#sequenceflow-end-white-black-e44tqyj3lmgalz7vnlb1g4ihr');\"/></g><polyline points=\"-215,140 -110,140 \" class=\"djs-hit djs-hit-stroke\" style=\"fill: none; stroke-opacity: 0; stroke: white; stroke-width: 15px;\"/><rect x=\"-221\" y=\"134\" width=\"117\" height=\"12\" class=\"djs-outline\" style=\"fill: none;\"/></g></g><g class=\"djs-group\"><g class=\"djs-element djs-connection\" data-element-id=\"Flow_09c7t0a\" style=\"display: block;\"><g class=\"djs-visual\"><path d=\"m  -10,140L102,140 \" style=\"fill: none; stroke-width: 2px; stroke: black; stroke-linejoin: round; marker-end: url('#sequenceflow-end-white-black-e44tqyj3lmgalz7vnlb1g4ihr');\"/></g><polyline points=\"-10,140 102,140 \" class=\"djs-hit djs-hit-stroke\" style=\"fill: none; stroke-opacity: 0; stroke: white; stroke-width: 15px;\"/><rect x=\"-16\" y=\"134\" width=\"124\" height=\"12\" class=\"djs-outline\" style=\"fill: none;\"/></g></g><g class=\"djs-group\"><g class=\"djs-element djs-shape\" data-element-id=\"startNode1\" style=\"display: block;\" transform=\"matrix(1 0 0 1 -245 125)\"><g class=\"djs-visual\"><circle cx=\"15\" cy=\"15\" r=\"15\" style=\"stroke: black; stroke-width: 2px; fill: white; fill-opacity: 0.95;\"/></g><rect class=\"djs-hit djs-hit-all\" x=\"0\" y=\"0\" width=\"30\" height=\"30\" style=\"fill: none; stroke-opacity: 0; stroke: white; stroke-width: 15px;\"/><rect x=\"-6\" y=\"-6\" width=\"42\" height=\"42\" class=\"djs-outline\" style=\"fill: none;\"/></g></g><g class=\"djs-group\"><g class=\"djs-element djs-shape\" data-element-id=\"startNode1_label\" style=\"display: block;\" transform=\"matrix(1 0 0 1 -242 162)\"><g class=\"djs-visual\"><text lineHeight=\"1.2\" class=\"djs-label\" style=\"font-family: Arial, sans-serif; font-size: 11px; font-weight: normal; fill: black;\"><tspan x=\"0\" y=\"9.899999999999999\">开始</tspan></text></g><rect class=\"djs-hit djs-hit-all\" x=\"0\" y=\"0\" width=\"22\" height=\"14\" style=\"fill: none; stroke-opacity: 0; stroke: white; stroke-width: 15px;\"/><rect x=\"-6\" y=\"-6\" width=\"34\" height=\"26\" class=\"djs-outline\" style=\"fill: none;\"/></g></g><g class=\"djs-group\"><g class=\"djs-element djs-shape\" data-element-id=\"Activity_09qbsq3\" style=\"display: block;\" transform=\"matrix(1 0 0 1 -110 100)\"><g class=\"djs-visual\"><rect x=\"0\" y=\"0\" width=\"100\" height=\"80\" rx=\"10\" ry=\"10\" style=\"stroke: black; stroke-width: 2px; fill: white; fill-opacity: 0.95;\"/><text lineHeight=\"1.2\" class=\"djs-label\" style=\"font-family: Arial, sans-serif; font-size: 12px; font-weight: normal; fill: black;\"><tspan x=\"50\" y=\"43.599999999999994\"/></text></g><rect class=\"djs-hit djs-hit-all\" x=\"0\" y=\"0\" width=\"100\" height=\"80\" style=\"fill: none; stroke-opacity: 0; stroke: white; stroke-width: 15px;\"/><rect x=\"-6\" y=\"-6\" width=\"112\" height=\"92\" class=\"djs-outline\" style=\"fill: none;\"/></g></g><g class=\"djs-group\"><g class=\"djs-element djs-shape selected\" data-element-id=\"Event_1elpj3f\" style=\"display: block;\" transform=\"matrix(1 0 0 1 102 122)\"><g class=\"djs-visual\"><circle cx=\"18\" cy=\"18\" r=\"18\" style=\"stroke: black; stroke-width: 4px; fill: white; fill-opacity: 0.95;\"/></g><rect class=\"djs-hit djs-hit-all\" x=\"0\" y=\"0\" width=\"36\" height=\"36\" style=\"fill: none; stroke-opacity: 0; stroke: white; stroke-width: 15px;\"/><rect x=\"-6\" y=\"-6\" width=\"48\" height=\"48\" class=\"djs-outline\" style=\"fill: none;\"/></g></g></svg>";

            InputStream svgStream = new ByteArrayInputStream(svgXml.getBytes(StandardCharsets.UTF_8));
            TranscoderInput input = new TranscoderInput(svgStream);
            PNGTranscoder transcoder = new PNGTranscoder();
            transcoder.addTranscodingHint(PNGTranscoder.KEY_FORCE_TRANSPARENT_WHITE, Boolean.TRUE);

            // Setup output
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            TranscoderOutput output = new TranscoderOutput(outStream);

            // Do the transformation
            transcoder.transcode(input, output);

            // Get the result
            final byte[] result = outStream.toByteArray();
            System.out.println(Arrays.toString(result));
            IoUtil.close(outStream);
            IoUtil.close(svgStream);
        } catch (TranscoderException e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        long elapsedTime = (endTime - startTime) / 1_000_000;
        System.out.println("Method execution time: " + elapsedTime + " ms");
    }

}
