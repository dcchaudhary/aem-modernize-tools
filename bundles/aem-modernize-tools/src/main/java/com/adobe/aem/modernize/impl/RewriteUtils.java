package com.adobe.aem.modernize.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.commons.lang3.StringUtils;

import com.day.cq.commons.jcr.JcrUtil;

/**
 * Provides helper methods to be used by rewrite rules.
 */

public class RewriteUtils {

    public static final String RESPONSIVE_GRID_RES_TYPE = "wcm/foundation/components/responsivegrid";
    /**
     * Checks if a node has a certain primary type.
     *
     * @param node The node to check
     * @param typeName The name of the primary type to check
     * @return true if the node has the specified primary type, false otherwise
     * @throws RepositoryException
     */
    public static boolean hasPrimaryType(Node node, String typeName)
            throws RepositoryException {
        return typeName != null && typeName.equals(node.getPrimaryNodeType().getName());
    }

    /**
     * Renames the specified node to a temporary name.
     *
     * @param node The node to be renamed
     * @throws RepositoryException
     */
    public static void rename(Node node)
            throws RepositoryException {
        Node destination = node.getParent();
        Session session = node.getSession();
        String tmpName = JcrUtil.createValidChildName(destination, "tmp-" + System.currentTimeMillis());
        String tmpPath = destination.getPath() + "/" + tmpName;
        session.move(node.getPath(), tmpPath);
    }

    /**
     * Convert a parameter key for osgi servcies into a map.
     * @param values
     * @param separator
     * @return
     */
    public static Map<String, String> toMap(final String[] values, final String separator) {
        final Map<String, String> map = new LinkedHashMap<>();

        if (values == null || values.length < 1) {
            return map;
        }

        for (final String value : values) {
            final String[] tmp = StringUtils.split(value, separator, 2 );

            if(tmp.length != 2) {
                continue;
            } else if (tmp.length == 2
                    && StringUtils.stripToNull(tmp[0]) != null
                    && StringUtils.stripToNull(tmp[1]) != null) {
                map.put(StringUtils.trim(tmp[0]), StringUtils.trim(tmp[1]));
            }
        }

        return map;
    }
}