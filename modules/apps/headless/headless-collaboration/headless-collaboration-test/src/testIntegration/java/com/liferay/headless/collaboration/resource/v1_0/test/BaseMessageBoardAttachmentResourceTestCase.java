/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.headless.collaboration.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import com.liferay.headless.collaboration.dto.v1_0.MessageBoardAttachment;
import com.liferay.headless.collaboration.resource.v1_0.MessageBoardAttachmentResource;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.multipart.MultipartBody;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.resource.EntityModelResource;

import java.lang.reflect.InvocationTargetException;

import java.net.URL;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.BeanUtilsBean;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public abstract class BaseMessageBoardAttachmentResourceTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	@Before
	public void setUp() throws Exception {
		irrelevantGroup = GroupTestUtil.addGroup();
		testGroup = GroupTestUtil.addGroup();

		_resourceURL = new URL(
			"http://localhost:8080/o/headless-collaboration/v1.0");
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(irrelevantGroup);
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testDeleteMessageBoardAttachment() throws Exception {
		MessageBoardAttachment messageBoardAttachment =
			testDeleteMessageBoardAttachment_addMessageBoardAttachment();

		assertResponseCode(
			204,
			invokeDeleteMessageBoardAttachmentResponse(
				messageBoardAttachment.getId()));

		assertResponseCode(
			404,
			invokeGetMessageBoardAttachmentResponse(
				messageBoardAttachment.getId()));
	}

	protected MessageBoardAttachment
			testDeleteMessageBoardAttachment_addMessageBoardAttachment()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void invokeDeleteMessageBoardAttachment(
			Long messageBoardAttachmentId)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setDelete(true);

		String location =
			_resourceURL +
				_toPath(
					"/message-board-attachments/{message-board-attachment-id}",
					messageBoardAttachmentId);

		options.setLocation(location);

		String string = HttpUtil.URLtoString(options);

		if (_log.isDebugEnabled()) {
			_log.debug("HTTP response: " + string);
		}
	}

	protected Http.Response invokeDeleteMessageBoardAttachmentResponse(
			Long messageBoardAttachmentId)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setDelete(true);

		String location =
			_resourceURL +
				_toPath(
					"/message-board-attachments/{message-board-attachment-id}",
					messageBoardAttachmentId);

		options.setLocation(location);

		HttpUtil.URLtoByteArray(options);

		return options.getResponse();
	}

	@Test
	public void testGetMessageBoardAttachment() throws Exception {
		MessageBoardAttachment postMessageBoardAttachment =
			testGetMessageBoardAttachment_addMessageBoardAttachment();

		MessageBoardAttachment getMessageBoardAttachment =
			invokeGetMessageBoardAttachment(postMessageBoardAttachment.getId());

		assertEquals(postMessageBoardAttachment, getMessageBoardAttachment);
		assertValid(getMessageBoardAttachment);
	}

	protected MessageBoardAttachment
			testGetMessageBoardAttachment_addMessageBoardAttachment()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected MessageBoardAttachment invokeGetMessageBoardAttachment(
			Long messageBoardAttachmentId)
		throws Exception {

		Http.Options options = _createHttpOptions();

		String location =
			_resourceURL +
				_toPath(
					"/message-board-attachments/{message-board-attachment-id}",
					messageBoardAttachmentId);

		options.setLocation(location);

		String string = HttpUtil.URLtoString(options);

		if (_log.isDebugEnabled()) {
			_log.debug("HTTP response: " + string);
		}

		try {
			return _outputObjectMapper.readValue(
				string, MessageBoardAttachment.class);
		}
		catch (Exception e) {
			_log.error("Unable to process HTTP response: " + string, e);

			throw e;
		}
	}

	protected Http.Response invokeGetMessageBoardAttachmentResponse(
			Long messageBoardAttachmentId)
		throws Exception {

		Http.Options options = _createHttpOptions();

		String location =
			_resourceURL +
				_toPath(
					"/message-board-attachments/{message-board-attachment-id}",
					messageBoardAttachmentId);

		options.setLocation(location);

		HttpUtil.URLtoByteArray(options);

		return options.getResponse();
	}

	@Test
	public void testGetMessageBoardMessageMessageBoardAttachmentsPage()
		throws Exception {

		Long messageBoardMessageId =
			testGetMessageBoardMessageMessageBoardAttachmentsPage_getMessageBoardMessageId();
		Long irrelevantMessageBoardMessageId =
			testGetMessageBoardMessageMessageBoardAttachmentsPage_getIrrelevantMessageBoardMessageId();

		if ((irrelevantMessageBoardMessageId != null)) {
			MessageBoardAttachment irrelevantMessageBoardAttachment =
				testGetMessageBoardMessageMessageBoardAttachmentsPage_addMessageBoardAttachment(
					irrelevantMessageBoardMessageId,
					randomIrrelevantMessageBoardAttachment());

			Page<MessageBoardAttachment> page =
				invokeGetMessageBoardMessageMessageBoardAttachmentsPage(
					irrelevantMessageBoardMessageId);

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantMessageBoardAttachment),
				(List<MessageBoardAttachment>)page.getItems());
			assertValid(page);
		}

		MessageBoardAttachment messageBoardAttachment1 =
			testGetMessageBoardMessageMessageBoardAttachmentsPage_addMessageBoardAttachment(
				messageBoardMessageId, randomMessageBoardAttachment());

		MessageBoardAttachment messageBoardAttachment2 =
			testGetMessageBoardMessageMessageBoardAttachmentsPage_addMessageBoardAttachment(
				messageBoardMessageId, randomMessageBoardAttachment());

		Page<MessageBoardAttachment> page =
			invokeGetMessageBoardMessageMessageBoardAttachmentsPage(
				messageBoardMessageId);

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(messageBoardAttachment1, messageBoardAttachment2),
			(List<MessageBoardAttachment>)page.getItems());
		assertValid(page);
	}

	protected MessageBoardAttachment
			testGetMessageBoardMessageMessageBoardAttachmentsPage_addMessageBoardAttachment(
				Long messageBoardMessageId,
				MessageBoardAttachment messageBoardAttachment)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long
			testGetMessageBoardMessageMessageBoardAttachmentsPage_getMessageBoardMessageId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long
			testGetMessageBoardMessageMessageBoardAttachmentsPage_getIrrelevantMessageBoardMessageId()
		throws Exception {

		return null;
	}

	protected Page<MessageBoardAttachment>
			invokeGetMessageBoardMessageMessageBoardAttachmentsPage(
				Long messageBoardMessageId)
		throws Exception {

		Http.Options options = _createHttpOptions();

		String location =
			_resourceURL +
				_toPath(
					"/message-board-messages/{message-board-message-id}/message-board-attachments",
					messageBoardMessageId);

		options.setLocation(location);

		String string = HttpUtil.URLtoString(options);

		if (_log.isDebugEnabled()) {
			_log.debug("HTTP response: " + string);
		}

		return _outputObjectMapper.readValue(
			string,
			new TypeReference<Page<MessageBoardAttachment>>() {
			});
	}

	protected Http.Response
			invokeGetMessageBoardMessageMessageBoardAttachmentsPageResponse(
				Long messageBoardMessageId)
		throws Exception {

		Http.Options options = _createHttpOptions();

		String location =
			_resourceURL +
				_toPath(
					"/message-board-messages/{message-board-message-id}/message-board-attachments",
					messageBoardMessageId);

		options.setLocation(location);

		HttpUtil.URLtoByteArray(options);

		return options.getResponse();
	}

	@Test
	public void testPostMessageBoardMessageMessageBoardAttachment()
		throws Exception {

		Assert.assertTrue(true);
	}

	protected MessageBoardAttachment
			testPostMessageBoardMessageMessageBoardAttachment_addMessageBoardAttachment(
				MessageBoardAttachment messageBoardAttachment)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected MessageBoardAttachment
			invokePostMessageBoardMessageMessageBoardAttachment(
				Long messageBoardMessageId, MultipartBody multipartBody)
		throws Exception {

		Http.Options options = _createHttpOptions();

		String location =
			_resourceURL +
				_toPath(
					"/message-board-messages/{message-board-message-id}/message-board-attachments",
					messageBoardMessageId);

		options.setLocation(location);

		options.setPost(true);

		String string = HttpUtil.URLtoString(options);

		if (_log.isDebugEnabled()) {
			_log.debug("HTTP response: " + string);
		}

		try {
			return _outputObjectMapper.readValue(
				string, MessageBoardAttachment.class);
		}
		catch (Exception e) {
			_log.error("Unable to process HTTP response: " + string, e);

			throw e;
		}
	}

	protected Http.Response
			invokePostMessageBoardMessageMessageBoardAttachmentResponse(
				Long messageBoardMessageId, MultipartBody multipartBody)
		throws Exception {

		Http.Options options = _createHttpOptions();

		String location =
			_resourceURL +
				_toPath(
					"/message-board-messages/{message-board-message-id}/message-board-attachments",
					messageBoardMessageId);

		options.setLocation(location);

		options.setPost(true);

		HttpUtil.URLtoByteArray(options);

		return options.getResponse();
	}

	@Test
	public void testGetMessageBoardThreadMessageBoardAttachmentsPage()
		throws Exception {

		Long messageBoardThreadId =
			testGetMessageBoardThreadMessageBoardAttachmentsPage_getMessageBoardThreadId();
		Long irrelevantMessageBoardThreadId =
			testGetMessageBoardThreadMessageBoardAttachmentsPage_getIrrelevantMessageBoardThreadId();

		if ((irrelevantMessageBoardThreadId != null)) {
			MessageBoardAttachment irrelevantMessageBoardAttachment =
				testGetMessageBoardThreadMessageBoardAttachmentsPage_addMessageBoardAttachment(
					irrelevantMessageBoardThreadId,
					randomIrrelevantMessageBoardAttachment());

			Page<MessageBoardAttachment> page =
				invokeGetMessageBoardThreadMessageBoardAttachmentsPage(
					irrelevantMessageBoardThreadId);

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantMessageBoardAttachment),
				(List<MessageBoardAttachment>)page.getItems());
			assertValid(page);
		}

		MessageBoardAttachment messageBoardAttachment1 =
			testGetMessageBoardThreadMessageBoardAttachmentsPage_addMessageBoardAttachment(
				messageBoardThreadId, randomMessageBoardAttachment());

		MessageBoardAttachment messageBoardAttachment2 =
			testGetMessageBoardThreadMessageBoardAttachmentsPage_addMessageBoardAttachment(
				messageBoardThreadId, randomMessageBoardAttachment());

		Page<MessageBoardAttachment> page =
			invokeGetMessageBoardThreadMessageBoardAttachmentsPage(
				messageBoardThreadId);

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(messageBoardAttachment1, messageBoardAttachment2),
			(List<MessageBoardAttachment>)page.getItems());
		assertValid(page);
	}

	protected MessageBoardAttachment
			testGetMessageBoardThreadMessageBoardAttachmentsPage_addMessageBoardAttachment(
				Long messageBoardThreadId,
				MessageBoardAttachment messageBoardAttachment)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long
			testGetMessageBoardThreadMessageBoardAttachmentsPage_getMessageBoardThreadId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long
			testGetMessageBoardThreadMessageBoardAttachmentsPage_getIrrelevantMessageBoardThreadId()
		throws Exception {

		return null;
	}

	protected Page<MessageBoardAttachment>
			invokeGetMessageBoardThreadMessageBoardAttachmentsPage(
				Long messageBoardThreadId)
		throws Exception {

		Http.Options options = _createHttpOptions();

		String location =
			_resourceURL +
				_toPath(
					"/message-board-threads/{message-board-thread-id}/message-board-attachments",
					messageBoardThreadId);

		options.setLocation(location);

		String string = HttpUtil.URLtoString(options);

		if (_log.isDebugEnabled()) {
			_log.debug("HTTP response: " + string);
		}

		return _outputObjectMapper.readValue(
			string,
			new TypeReference<Page<MessageBoardAttachment>>() {
			});
	}

	protected Http.Response
			invokeGetMessageBoardThreadMessageBoardAttachmentsPageResponse(
				Long messageBoardThreadId)
		throws Exception {

		Http.Options options = _createHttpOptions();

		String location =
			_resourceURL +
				_toPath(
					"/message-board-threads/{message-board-thread-id}/message-board-attachments",
					messageBoardThreadId);

		options.setLocation(location);

		HttpUtil.URLtoByteArray(options);

		return options.getResponse();
	}

	@Test
	public void testPostMessageBoardThreadMessageBoardAttachment()
		throws Exception {

		Assert.assertTrue(true);
	}

	protected MessageBoardAttachment
			testPostMessageBoardThreadMessageBoardAttachment_addMessageBoardAttachment(
				MessageBoardAttachment messageBoardAttachment)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected MessageBoardAttachment
			invokePostMessageBoardThreadMessageBoardAttachment(
				Long messageBoardThreadId, MultipartBody multipartBody)
		throws Exception {

		Http.Options options = _createHttpOptions();

		String location =
			_resourceURL +
				_toPath(
					"/message-board-threads/{message-board-thread-id}/message-board-attachments",
					messageBoardThreadId);

		options.setLocation(location);

		options.setPost(true);

		String string = HttpUtil.URLtoString(options);

		if (_log.isDebugEnabled()) {
			_log.debug("HTTP response: " + string);
		}

		try {
			return _outputObjectMapper.readValue(
				string, MessageBoardAttachment.class);
		}
		catch (Exception e) {
			_log.error("Unable to process HTTP response: " + string, e);

			throw e;
		}
	}

	protected Http.Response
			invokePostMessageBoardThreadMessageBoardAttachmentResponse(
				Long messageBoardThreadId, MultipartBody multipartBody)
		throws Exception {

		Http.Options options = _createHttpOptions();

		String location =
			_resourceURL +
				_toPath(
					"/message-board-threads/{message-board-thread-id}/message-board-attachments",
					messageBoardThreadId);

		options.setLocation(location);

		options.setPost(true);

		HttpUtil.URLtoByteArray(options);

		return options.getResponse();
	}

	protected void assertResponseCode(
		int expectedResponseCode, Http.Response actualResponse) {

		Assert.assertEquals(
			expectedResponseCode, actualResponse.getResponseCode());
	}

	protected void assertEquals(
		MessageBoardAttachment messageBoardAttachment1,
		MessageBoardAttachment messageBoardAttachment2) {

		Assert.assertTrue(
			messageBoardAttachment1 + " does not equal " +
				messageBoardAttachment2,
			equals(messageBoardAttachment1, messageBoardAttachment2));
	}

	protected void assertEquals(
		List<MessageBoardAttachment> messageBoardAttachments1,
		List<MessageBoardAttachment> messageBoardAttachments2) {

		Assert.assertEquals(
			messageBoardAttachments1.size(), messageBoardAttachments2.size());

		for (int i = 0; i < messageBoardAttachments1.size(); i++) {
			MessageBoardAttachment messageBoardAttachment1 =
				messageBoardAttachments1.get(i);
			MessageBoardAttachment messageBoardAttachment2 =
				messageBoardAttachments2.get(i);

			assertEquals(messageBoardAttachment1, messageBoardAttachment2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<MessageBoardAttachment> messageBoardAttachments1,
		List<MessageBoardAttachment> messageBoardAttachments2) {

		Assert.assertEquals(
			messageBoardAttachments1.size(), messageBoardAttachments2.size());

		for (MessageBoardAttachment messageBoardAttachment1 :
				messageBoardAttachments1) {

			boolean contains = false;

			for (MessageBoardAttachment messageBoardAttachment2 :
					messageBoardAttachments2) {

				if (equals(messageBoardAttachment1, messageBoardAttachment2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				messageBoardAttachments2 + " does not contain " +
					messageBoardAttachment1,
				contains);
		}
	}

	protected void assertValid(MessageBoardAttachment messageBoardAttachment) {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertValid(Page<MessageBoardAttachment> page) {
		boolean valid = false;

		Collection<MessageBoardAttachment> messageBoardAttachments =
			page.getItems();

		int size = messageBoardAttachments.size();

		if ((page.getLastPage() > 0) && (page.getPage() > 0) &&
			(page.getPageSize() > 0) && (page.getTotalCount() > 0) &&
			(size > 0)) {

			valid = true;
		}

		Assert.assertTrue(valid);
	}

	protected boolean equals(
		MessageBoardAttachment messageBoardAttachment1,
		MessageBoardAttachment messageBoardAttachment2) {

		if (messageBoardAttachment1 == messageBoardAttachment2) {
			return true;
		}

		return false;
	}

	protected Collection<EntityField> getEntityFields() throws Exception {
		if (!(_messageBoardAttachmentResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_messageBoardAttachmentResource;

		EntityModel entityModel = entityModelResource.getEntityModel(
			new MultivaluedHashMap());

		Map<String, EntityField> entityFieldsMap =
			entityModel.getEntityFieldsMap();

		return entityFieldsMap.values();
	}

	protected List<EntityField> getEntityFields(EntityField.Type type)
		throws Exception {

		Collection<EntityField> entityFields = getEntityFields();

		Stream<EntityField> stream = entityFields.stream();

		return stream.filter(
			entityField -> Objects.equals(entityField.getType(), type)
		).collect(
			Collectors.toList()
		);
	}

	protected String getFilterString(
		EntityField entityField, String operator,
		MessageBoardAttachment messageBoardAttachment) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("contentUrl")) {
			sb.append("'");
			sb.append(String.valueOf(messageBoardAttachment.getContentUrl()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("encodingFormat")) {
			sb.append("'");
			sb.append(
				String.valueOf(messageBoardAttachment.getEncodingFormat()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("fileExtension")) {
			sb.append("'");
			sb.append(
				String.valueOf(messageBoardAttachment.getFileExtension()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("id")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("sizeInBytes")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("title")) {
			sb.append("'");
			sb.append(String.valueOf(messageBoardAttachment.getTitle()));
			sb.append("'");

			return sb.toString();
		}

		throw new IllegalArgumentException(
			"Invalid entity field " + entityFieldName);
	}

	protected MessageBoardAttachment randomMessageBoardAttachment() {
		return new MessageBoardAttachment() {
			{
				contentUrl = RandomTestUtil.randomString();
				encodingFormat = RandomTestUtil.randomString();
				fileExtension = RandomTestUtil.randomString();
				id = RandomTestUtil.randomLong();
				title = RandomTestUtil.randomString();
			}
		};
	}

	protected MessageBoardAttachment randomIrrelevantMessageBoardAttachment() {
		return randomMessageBoardAttachment();
	}

	protected MessageBoardAttachment randomPatchMessageBoardAttachment() {
		return randomMessageBoardAttachment();
	}

	protected Group irrelevantGroup;
	protected Group testGroup;

	protected static class Page<T> {

		public Collection<T> getItems() {
			return new ArrayList<>(items);
		}

		public long getLastPage() {
			return lastPage;
		}

		public long getPage() {
			return page;
		}

		public long getPageSize() {
			return pageSize;
		}

		public long getTotalCount() {
			return totalCount;
		}

		@JsonProperty
		protected Collection<T> items;

		@JsonProperty
		protected long lastPage;

		@JsonProperty
		protected long page;

		@JsonProperty
		protected long pageSize;

		@JsonProperty
		protected long totalCount;

	}

	private Http.Options _createHttpOptions() {
		Http.Options options = new Http.Options();

		options.addHeader("Accept", "application/json");

		String userNameAndPassword = "test@liferay.com:test";

		String encodedUserNameAndPassword = Base64.encode(
			userNameAndPassword.getBytes());

		options.addHeader(
			"Authorization", "Basic " + encodedUserNameAndPassword);

		options.addHeader("Content-Type", "application/json");

		return options;
	}

	private String _toPath(String template, Object... values) {
		if (ArrayUtil.isEmpty(values)) {
			return template;
		}

		for (int i = 0; i < values.length; i++) {
			template = template.replaceFirst(
				"\\{.*?\\}", String.valueOf(values[i]));
		}

		return template;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseMessageBoardAttachmentResourceTestCase.class);

	private static BeanUtilsBean _beanUtilsBean = new BeanUtilsBean() {

		@Override
		public void copyProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {

			if (value != null) {
				super.copyProperty(bean, name, value);
			}
		}

	};
	private static DateFormat _dateFormat;
	private final static ObjectMapper _inputObjectMapper = new ObjectMapper() {
		{
			setFilterProvider(
				new SimpleFilterProvider() {
					{
						addFilter(
							"Liferay.Vulcan",
							SimpleBeanPropertyFilter.serializeAll());
					}
				});
			setSerializationInclusion(JsonInclude.Include.NON_NULL);
		}
	};
	private final static ObjectMapper _outputObjectMapper = new ObjectMapper() {
		{
			setFilterProvider(
				new SimpleFilterProvider() {
					{
						addFilter(
							"Liferay.Vulcan",
							SimpleBeanPropertyFilter.serializeAll());
					}
				});
		}
	};

	@Inject
	private MessageBoardAttachmentResource _messageBoardAttachmentResource;

	private URL _resourceURL;

}