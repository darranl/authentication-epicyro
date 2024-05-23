/*
 * Copyright The WildFly Authors
 * SPDX-License-Identifier: Apache-2.0
 */
package org.wildfly.security.extension.authentication.epicyro;

import static org.wildfly.security.extension.authentication.epicyro.AuthenticationEpicyroExtension.SUBSYSTEM_NAME;
import static org.wildfly.security.extension.authentication.epicyro.AuthenticationEpicyroExtension.SUBSYSTEM_PATH;

import org.jboss.as.controller.ResourceDefinition;
import org.jboss.as.controller.ResourceRegistration;
import org.jboss.as.controller.SubsystemRegistration;
import org.jboss.as.controller.descriptions.ParentResourceDescriptionResolver;
import org.jboss.as.controller.descriptions.SubsystemResourceDescriptionResolver;
import org.jboss.as.controller.registry.ManagementResourceRegistration;
import org.jboss.as.controller.registry.RuntimePackageDependency;
import org.jboss.as.server.DeploymentProcessorTarget;
import org.wildfly.subsystem.resource.ManagementResourceRegistrar;
import org.wildfly.subsystem.resource.ManagementResourceRegistrationContext;
import org.wildfly.subsystem.resource.ResourceDescriptor;
import org.wildfly.subsystem.resource.SubsystemResourceDefinitionRegistrar;

/**
 * Resource definition for the authentication-epicyro subsystem root resource.
 *
 * @author <a href="mailto:darran.lofthouse@jboss.com">Darran Lofthouse</a>
 */
final class AuthenticationEpicyroSubsystemRegistrar implements SubsystemResourceDefinitionRegistrar {

    static final ParentResourceDescriptionResolver RESOLVER = new SubsystemResourceDescriptionResolver(SUBSYSTEM_NAME, AuthenticationEpicyroSubsystemRegistrar.class);

    @Override
    public ManagementResourceRegistration register(SubsystemRegistration parent, ManagementResourceRegistrationContext managementResourceRegistrationContext) {
        ResourceDefinition definition = ResourceDefinition.builder(ResourceRegistration.of(SUBSYSTEM_PATH), RESOLVER).build();
        ManagementResourceRegistration registration = parent.registerSubsystemModel(definition);
        ResourceDescriptor descriptor = ResourceDescriptor.builder(RESOLVER)
                .build();
        ManagementResourceRegistrar.of(descriptor).register(registration);

        return registration;
    }

}
