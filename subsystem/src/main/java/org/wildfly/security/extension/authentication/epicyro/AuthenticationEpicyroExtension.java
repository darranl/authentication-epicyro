/*
 * Copyright The WildFly Authors
 * SPDX-License-Identifier: Apache-2.0
 */
package org.wildfly.security.extension.authentication.epicyro;

import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.SUBSYSTEM;

import org.jboss.as.controller.ModelVersion;
import org.jboss.as.controller.PathElement;
import org.jboss.as.controller.PersistentResourceXMLDescription;
import org.jboss.as.controller.PersistentSubsystemSchema;
import org.jboss.as.controller.SubsystemModel;
import org.jboss.as.controller.SubsystemSchema;
import org.jboss.as.controller.xml.VersionedNamespace;
import org.jboss.as.version.Stability;
import org.jboss.staxmapper.IntVersion;
import org.wildfly.subsystem.SubsystemConfiguration;
import org.wildfly.subsystem.SubsystemExtension;
import org.wildfly.subsystem.SubsystemPersistence;


/**
 * WildFly extension that provides Jakarta Authentication support based on Eclipse Epicyro.
 *
 * @author <a href="mailto:darran.lofthouse@jboss.com">Darran Lofthouse</a>
 */
public final class AuthenticationEpicyroExtension extends SubsystemExtension<AuthenticationEpicyroExtension.ApplicationEpicyroSubsystemSchema> {

    /**
     * The name of our subsystem within the model.
     */
    static final String SUBSYSTEM_NAME = "authentication-epicyro";
    private static final Stability FEATURE_STABILITY = Stability.PREVIEW;

    static final PathElement SUBSYSTEM_PATH = PathElement.pathElement(SUBSYSTEM, SUBSYSTEM_NAME);

    public AuthenticationEpicyroExtension() {
        super(SubsystemConfiguration.of(SUBSYSTEM_NAME, AuthenticationEpicyroSubsystemModel.CURRENT, AuthenticationEpicyroSubsystemRegistrar::new),
                SubsystemPersistence.of(ApplicationEpicyroSubsystemSchema.CURRENT));
    }

    /**
     * Model for the 'authentication-epicyro' subsystem.
     */
    public enum AuthenticationEpicyroSubsystemModel implements SubsystemModel {
        VERSION_1_0_0(1, 0, 0),
        ;

        static final AuthenticationEpicyroSubsystemModel CURRENT = VERSION_1_0_0;

        private final ModelVersion version;

        AuthenticationEpicyroSubsystemModel(int major, int minor, int micro) {
            this.version = ModelVersion.create(major, minor, micro);
        }

        @Override
        public ModelVersion getVersion() {
            return this.version;
        }
    }

    @Override
    public Stability getStability() {
        return Stability.PREVIEW;
    }

    /**
     * Schema for the 'application-epicyro' subsystem.
     */
    public enum ApplicationEpicyroSubsystemSchema implements PersistentSubsystemSchema<ApplicationEpicyroSubsystemSchema> {

        VERSION_1_0_PREVIEW(1, 0, FEATURE_STABILITY)
        ;

        static final ApplicationEpicyroSubsystemSchema CURRENT = VERSION_1_0_PREVIEW;

        private final VersionedNamespace<IntVersion, ApplicationEpicyroSubsystemSchema> namespace;

        ApplicationEpicyroSubsystemSchema(int major, int minor, Stability stability) {
            this.namespace = SubsystemSchema.createSubsystemURN(SUBSYSTEM_NAME, stability, new IntVersion(major, minor));
        }

        @Override
        public VersionedNamespace<IntVersion, ApplicationEpicyroSubsystemSchema> getNamespace() {
            return this.namespace;
        }

        @Override
        public Stability getStability() {
            return Stability.PREVIEW;
        }

        @Override
        public PersistentResourceXMLDescription getXMLDescription() {
            PersistentResourceXMLDescription.Factory factory = PersistentResourceXMLDescription.factory(this);
            return factory.builder(SUBSYSTEM_PATH)
                    .build();
        }
    }
}
