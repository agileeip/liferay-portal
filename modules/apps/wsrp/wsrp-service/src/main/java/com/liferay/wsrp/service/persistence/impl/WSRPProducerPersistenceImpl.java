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

package com.liferay.wsrp.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.liferay.wsrp.exception.NoSuchProducerException;
import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.model.impl.WSRPProducerImpl;
import com.liferay.wsrp.model.impl.WSRPProducerModelImpl;
import com.liferay.wsrp.service.persistence.WSRPProducerPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the wsrp producer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WSRPProducerPersistence
 * @see com.liferay.wsrp.service.persistence.WSRPProducerUtil
 * @generated
 */
@ProviderType
public class WSRPProducerPersistenceImpl extends BasePersistenceImpl<WSRPProducer>
	implements WSRPProducerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WSRPProducerUtil} to access the wsrp producer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WSRPProducerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the wsrp producers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching wsrp producers
	 */
	@Override
	public List<WSRPProducer> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the wsrp producers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of wsrp producers
	 * @param end the upper bound of the range of wsrp producers (not inclusive)
	 * @return the range of matching wsrp producers
	 */
	@Override
	public List<WSRPProducer> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the wsrp producers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of wsrp producers
	 * @param end the upper bound of the range of wsrp producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching wsrp producers
	 */
	@Override
	public List<WSRPProducer> findByUuid(String uuid, int start, int end,
		OrderByComparator<WSRPProducer> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the wsrp producers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of wsrp producers
	 * @param end the upper bound of the range of wsrp producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching wsrp producers
	 */
	@Override
	public List<WSRPProducer> findByUuid(String uuid, int start, int end,
		OrderByComparator<WSRPProducer> orderByComparator,
		boolean retrieveFromCache) {
		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByUuid;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<WSRPProducer> list = null;

		if (retrieveFromCache) {
			list = (List<WSRPProducer>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (WSRPProducer wsrpProducer : list) {
					if (!uuid.equals(wsrpProducer.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_WSRPPRODUCER_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WSRPProducerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<WSRPProducer>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WSRPProducer>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first wsrp producer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wsrp producer
	 * @throws NoSuchProducerException if a matching wsrp producer could not be found
	 */
	@Override
	public WSRPProducer findByUuid_First(String uuid,
		OrderByComparator<WSRPProducer> orderByComparator)
		throws NoSuchProducerException {
		WSRPProducer wsrpProducer = fetchByUuid_First(uuid, orderByComparator);

		if (wsrpProducer != null) {
			return wsrpProducer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchProducerException(msg.toString());
	}

	/**
	 * Returns the first wsrp producer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wsrp producer, or <code>null</code> if a matching wsrp producer could not be found
	 */
	@Override
	public WSRPProducer fetchByUuid_First(String uuid,
		OrderByComparator<WSRPProducer> orderByComparator) {
		List<WSRPProducer> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last wsrp producer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wsrp producer
	 * @throws NoSuchProducerException if a matching wsrp producer could not be found
	 */
	@Override
	public WSRPProducer findByUuid_Last(String uuid,
		OrderByComparator<WSRPProducer> orderByComparator)
		throws NoSuchProducerException {
		WSRPProducer wsrpProducer = fetchByUuid_Last(uuid, orderByComparator);

		if (wsrpProducer != null) {
			return wsrpProducer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchProducerException(msg.toString());
	}

	/**
	 * Returns the last wsrp producer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wsrp producer, or <code>null</code> if a matching wsrp producer could not be found
	 */
	@Override
	public WSRPProducer fetchByUuid_Last(String uuid,
		OrderByComparator<WSRPProducer> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<WSRPProducer> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the wsrp producers before and after the current wsrp producer in the ordered set where uuid = &#63;.
	 *
	 * @param wsrpProducerId the primary key of the current wsrp producer
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next wsrp producer
	 * @throws NoSuchProducerException if a wsrp producer with the primary key could not be found
	 */
	@Override
	public WSRPProducer[] findByUuid_PrevAndNext(long wsrpProducerId,
		String uuid, OrderByComparator<WSRPProducer> orderByComparator)
		throws NoSuchProducerException {
		uuid = Objects.toString(uuid, "");

		WSRPProducer wsrpProducer = findByPrimaryKey(wsrpProducerId);

		Session session = null;

		try {
			session = openSession();

			WSRPProducer[] array = new WSRPProducerImpl[3];

			array[0] = getByUuid_PrevAndNext(session, wsrpProducer, uuid,
					orderByComparator, true);

			array[1] = wsrpProducer;

			array[2] = getByUuid_PrevAndNext(session, wsrpProducer, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WSRPProducer getByUuid_PrevAndNext(Session session,
		WSRPProducer wsrpProducer, String uuid,
		OrderByComparator<WSRPProducer> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WSRPPRODUCER_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(WSRPProducerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					wsrpProducer)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<WSRPProducer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the wsrp producers where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (WSRPProducer wsrpProducer : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(wsrpProducer);
		}
	}

	/**
	 * Returns the number of wsrp producers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching wsrp producers
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WSRPPRODUCER_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 = "wsrpProducer.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(wsrpProducer.uuid IS NULL OR wsrpProducer.uuid = '')";
	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the wsrp producer where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchProducerException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching wsrp producer
	 * @throws NoSuchProducerException if a matching wsrp producer could not be found
	 */
	@Override
	public WSRPProducer findByUUID_G(String uuid, long groupId)
		throws NoSuchProducerException {
		WSRPProducer wsrpProducer = fetchByUUID_G(uuid, groupId);

		if (wsrpProducer == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchProducerException(msg.toString());
		}

		return wsrpProducer;
	}

	/**
	 * Returns the wsrp producer where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching wsrp producer, or <code>null</code> if a matching wsrp producer could not be found
	 */
	@Override
	public WSRPProducer fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the wsrp producer where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching wsrp producer, or <code>null</code> if a matching wsrp producer could not be found
	 */
	@Override
	public WSRPProducer fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(_finderPathFetchByUUID_G,
					finderArgs, this);
		}

		if (result instanceof WSRPProducer) {
			WSRPProducer wsrpProducer = (WSRPProducer)result;

			if (!Objects.equals(uuid, wsrpProducer.getUuid()) ||
					(groupId != wsrpProducer.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_WSRPPRODUCER_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<WSRPProducer> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(_finderPathFetchByUUID_G, finderArgs,
						list);
				}
				else {
					WSRPProducer wsrpProducer = list.get(0);

					result = wsrpProducer;

					cacheResult(wsrpProducer);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByUUID_G, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (WSRPProducer)result;
		}
	}

	/**
	 * Removes the wsrp producer where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the wsrp producer that was removed
	 */
	@Override
	public WSRPProducer removeByUUID_G(String uuid, long groupId)
		throws NoSuchProducerException {
		WSRPProducer wsrpProducer = findByUUID_G(uuid, groupId);

		return remove(wsrpProducer);
	}

	/**
	 * Returns the number of wsrp producers where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching wsrp producers
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WSRPPRODUCER_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "wsrpProducer.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(wsrpProducer.uuid IS NULL OR wsrpProducer.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "wsrpProducer.groupId = ?";
	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the wsrp producers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching wsrp producers
	 */
	@Override
	public List<WSRPProducer> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the wsrp producers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of wsrp producers
	 * @param end the upper bound of the range of wsrp producers (not inclusive)
	 * @return the range of matching wsrp producers
	 */
	@Override
	public List<WSRPProducer> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the wsrp producers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of wsrp producers
	 * @param end the upper bound of the range of wsrp producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching wsrp producers
	 */
	@Override
	public List<WSRPProducer> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<WSRPProducer> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the wsrp producers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of wsrp producers
	 * @param end the upper bound of the range of wsrp producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching wsrp producers
	 */
	@Override
	public List<WSRPProducer> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<WSRPProducer> orderByComparator,
		boolean retrieveFromCache) {
		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByUuid_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<WSRPProducer> list = null;

		if (retrieveFromCache) {
			list = (List<WSRPProducer>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (WSRPProducer wsrpProducer : list) {
					if (!uuid.equals(wsrpProducer.getUuid()) ||
							(companyId != wsrpProducer.getCompanyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_WSRPPRODUCER_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WSRPProducerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<WSRPProducer>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WSRPProducer>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first wsrp producer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wsrp producer
	 * @throws NoSuchProducerException if a matching wsrp producer could not be found
	 */
	@Override
	public WSRPProducer findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<WSRPProducer> orderByComparator)
		throws NoSuchProducerException {
		WSRPProducer wsrpProducer = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (wsrpProducer != null) {
			return wsrpProducer;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchProducerException(msg.toString());
	}

	/**
	 * Returns the first wsrp producer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wsrp producer, or <code>null</code> if a matching wsrp producer could not be found
	 */
	@Override
	public WSRPProducer fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<WSRPProducer> orderByComparator) {
		List<WSRPProducer> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last wsrp producer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wsrp producer
	 * @throws NoSuchProducerException if a matching wsrp producer could not be found
	 */
	@Override
	public WSRPProducer findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<WSRPProducer> orderByComparator)
		throws NoSuchProducerException {
		WSRPProducer wsrpProducer = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (wsrpProducer != null) {
			return wsrpProducer;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchProducerException(msg.toString());
	}

	/**
	 * Returns the last wsrp producer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wsrp producer, or <code>null</code> if a matching wsrp producer could not be found
	 */
	@Override
	public WSRPProducer fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<WSRPProducer> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<WSRPProducer> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the wsrp producers before and after the current wsrp producer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param wsrpProducerId the primary key of the current wsrp producer
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next wsrp producer
	 * @throws NoSuchProducerException if a wsrp producer with the primary key could not be found
	 */
	@Override
	public WSRPProducer[] findByUuid_C_PrevAndNext(long wsrpProducerId,
		String uuid, long companyId,
		OrderByComparator<WSRPProducer> orderByComparator)
		throws NoSuchProducerException {
		uuid = Objects.toString(uuid, "");

		WSRPProducer wsrpProducer = findByPrimaryKey(wsrpProducerId);

		Session session = null;

		try {
			session = openSession();

			WSRPProducer[] array = new WSRPProducerImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, wsrpProducer, uuid,
					companyId, orderByComparator, true);

			array[1] = wsrpProducer;

			array[2] = getByUuid_C_PrevAndNext(session, wsrpProducer, uuid,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WSRPProducer getByUuid_C_PrevAndNext(Session session,
		WSRPProducer wsrpProducer, String uuid, long companyId,
		OrderByComparator<WSRPProducer> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_WSRPPRODUCER_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(WSRPProducerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					wsrpProducer)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<WSRPProducer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the wsrp producers where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (WSRPProducer wsrpProducer : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(wsrpProducer);
		}
	}

	/**
	 * Returns the number of wsrp producers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching wsrp producers
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WSRPPRODUCER_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "wsrpProducer.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(wsrpProducer.uuid IS NULL OR wsrpProducer.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "wsrpProducer.companyId = ?";
	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the wsrp producers where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching wsrp producers
	 */
	@Override
	public List<WSRPProducer> findByCompanyId(long companyId) {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the wsrp producers where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of wsrp producers
	 * @param end the upper bound of the range of wsrp producers (not inclusive)
	 * @return the range of matching wsrp producers
	 */
	@Override
	public List<WSRPProducer> findByCompanyId(long companyId, int start, int end) {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the wsrp producers where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of wsrp producers
	 * @param end the upper bound of the range of wsrp producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching wsrp producers
	 */
	@Override
	public List<WSRPProducer> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<WSRPProducer> orderByComparator) {
		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the wsrp producers where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of wsrp producers
	 * @param end the upper bound of the range of wsrp producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching wsrp producers
	 */
	@Override
	public List<WSRPProducer> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<WSRPProducer> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByCompanyId;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = _finderPathWithPaginationFindByCompanyId;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<WSRPProducer> list = null;

		if (retrieveFromCache) {
			list = (List<WSRPProducer>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (WSRPProducer wsrpProducer : list) {
					if ((companyId != wsrpProducer.getCompanyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_WSRPPRODUCER_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WSRPProducerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<WSRPProducer>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WSRPProducer>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first wsrp producer in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wsrp producer
	 * @throws NoSuchProducerException if a matching wsrp producer could not be found
	 */
	@Override
	public WSRPProducer findByCompanyId_First(long companyId,
		OrderByComparator<WSRPProducer> orderByComparator)
		throws NoSuchProducerException {
		WSRPProducer wsrpProducer = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (wsrpProducer != null) {
			return wsrpProducer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchProducerException(msg.toString());
	}

	/**
	 * Returns the first wsrp producer in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wsrp producer, or <code>null</code> if a matching wsrp producer could not be found
	 */
	@Override
	public WSRPProducer fetchByCompanyId_First(long companyId,
		OrderByComparator<WSRPProducer> orderByComparator) {
		List<WSRPProducer> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last wsrp producer in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wsrp producer
	 * @throws NoSuchProducerException if a matching wsrp producer could not be found
	 */
	@Override
	public WSRPProducer findByCompanyId_Last(long companyId,
		OrderByComparator<WSRPProducer> orderByComparator)
		throws NoSuchProducerException {
		WSRPProducer wsrpProducer = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (wsrpProducer != null) {
			return wsrpProducer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchProducerException(msg.toString());
	}

	/**
	 * Returns the last wsrp producer in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wsrp producer, or <code>null</code> if a matching wsrp producer could not be found
	 */
	@Override
	public WSRPProducer fetchByCompanyId_Last(long companyId,
		OrderByComparator<WSRPProducer> orderByComparator) {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<WSRPProducer> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the wsrp producers before and after the current wsrp producer in the ordered set where companyId = &#63;.
	 *
	 * @param wsrpProducerId the primary key of the current wsrp producer
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next wsrp producer
	 * @throws NoSuchProducerException if a wsrp producer with the primary key could not be found
	 */
	@Override
	public WSRPProducer[] findByCompanyId_PrevAndNext(long wsrpProducerId,
		long companyId, OrderByComparator<WSRPProducer> orderByComparator)
		throws NoSuchProducerException {
		WSRPProducer wsrpProducer = findByPrimaryKey(wsrpProducerId);

		Session session = null;

		try {
			session = openSession();

			WSRPProducer[] array = new WSRPProducerImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, wsrpProducer,
					companyId, orderByComparator, true);

			array[1] = wsrpProducer;

			array[2] = getByCompanyId_PrevAndNext(session, wsrpProducer,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WSRPProducer getByCompanyId_PrevAndNext(Session session,
		WSRPProducer wsrpProducer, long companyId,
		OrderByComparator<WSRPProducer> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WSRPPRODUCER_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(WSRPProducerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue : orderByComparator.getOrderByConditionValues(
					wsrpProducer)) {
				qPos.add(orderByConditionValue);
			}
		}

		List<WSRPProducer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the wsrp producers where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (WSRPProducer wsrpProducer : findByCompanyId(companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(wsrpProducer);
		}
	}

	/**
	 * Returns the number of wsrp producers where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching wsrp producers
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = _finderPathCountByCompanyId;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WSRPPRODUCER_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "wsrpProducer.companyId = ?";

	public WSRPProducerPersistenceImpl() {
		setModelClass(WSRPProducer.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the wsrp producer in the entity cache if it is enabled.
	 *
	 * @param wsrpProducer the wsrp producer
	 */
	@Override
	public void cacheResult(WSRPProducer wsrpProducer) {
		entityCache.putResult(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerImpl.class, wsrpProducer.getPrimaryKey(), wsrpProducer);

		finderCache.putResult(_finderPathFetchByUUID_G,
			new Object[] { wsrpProducer.getUuid(), wsrpProducer.getGroupId() },
			wsrpProducer);

		wsrpProducer.resetOriginalValues();
	}

	/**
	 * Caches the wsrp producers in the entity cache if it is enabled.
	 *
	 * @param wsrpProducers the wsrp producers
	 */
	@Override
	public void cacheResult(List<WSRPProducer> wsrpProducers) {
		for (WSRPProducer wsrpProducer : wsrpProducers) {
			if (entityCache.getResult(
						WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
						WSRPProducerImpl.class, wsrpProducer.getPrimaryKey()) == null) {
				cacheResult(wsrpProducer);
			}
			else {
				wsrpProducer.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all wsrp producers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WSRPProducerImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the wsrp producer.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WSRPProducer wsrpProducer) {
		entityCache.removeResult(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerImpl.class, wsrpProducer.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((WSRPProducerModelImpl)wsrpProducer, true);
	}

	@Override
	public void clearCache(List<WSRPProducer> wsrpProducers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WSRPProducer wsrpProducer : wsrpProducers) {
			entityCache.removeResult(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
				WSRPProducerImpl.class, wsrpProducer.getPrimaryKey());

			clearUniqueFindersCache((WSRPProducerModelImpl)wsrpProducer, true);
		}
	}

	protected void cacheUniqueFindersCache(
		WSRPProducerModelImpl wsrpProducerModelImpl) {
		Object[] args = new Object[] {
				wsrpProducerModelImpl.getUuid(),
				wsrpProducerModelImpl.getGroupId()
			};

		finderCache.putResult(_finderPathCountByUUID_G, args, Long.valueOf(1),
			false);
		finderCache.putResult(_finderPathFetchByUUID_G, args,
			wsrpProducerModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		WSRPProducerModelImpl wsrpProducerModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					wsrpProducerModelImpl.getUuid(),
					wsrpProducerModelImpl.getGroupId()
				};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((wsrpProducerModelImpl.getColumnBitmask() &
				_finderPathFetchByUUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					wsrpProducerModelImpl.getOriginalUuid(),
					wsrpProducerModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}
	}

	/**
	 * Creates a new wsrp producer with the primary key. Does not add the wsrp producer to the database.
	 *
	 * @param wsrpProducerId the primary key for the new wsrp producer
	 * @return the new wsrp producer
	 */
	@Override
	public WSRPProducer create(long wsrpProducerId) {
		WSRPProducer wsrpProducer = new WSRPProducerImpl();

		wsrpProducer.setNew(true);
		wsrpProducer.setPrimaryKey(wsrpProducerId);

		String uuid = PortalUUIDUtil.generate();

		wsrpProducer.setUuid(uuid);

		wsrpProducer.setCompanyId(companyProvider.getCompanyId());

		return wsrpProducer;
	}

	/**
	 * Removes the wsrp producer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param wsrpProducerId the primary key of the wsrp producer
	 * @return the wsrp producer that was removed
	 * @throws NoSuchProducerException if a wsrp producer with the primary key could not be found
	 */
	@Override
	public WSRPProducer remove(long wsrpProducerId)
		throws NoSuchProducerException {
		return remove((Serializable)wsrpProducerId);
	}

	/**
	 * Removes the wsrp producer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the wsrp producer
	 * @return the wsrp producer that was removed
	 * @throws NoSuchProducerException if a wsrp producer with the primary key could not be found
	 */
	@Override
	public WSRPProducer remove(Serializable primaryKey)
		throws NoSuchProducerException {
		Session session = null;

		try {
			session = openSession();

			WSRPProducer wsrpProducer = (WSRPProducer)session.get(WSRPProducerImpl.class,
					primaryKey);

			if (wsrpProducer == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProducerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(wsrpProducer);
		}
		catch (NoSuchProducerException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected WSRPProducer removeImpl(WSRPProducer wsrpProducer) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(wsrpProducer)) {
				wsrpProducer = (WSRPProducer)session.get(WSRPProducerImpl.class,
						wsrpProducer.getPrimaryKeyObj());
			}

			if (wsrpProducer != null) {
				session.delete(wsrpProducer);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (wsrpProducer != null) {
			clearCache(wsrpProducer);
		}

		return wsrpProducer;
	}

	@Override
	public WSRPProducer updateImpl(WSRPProducer wsrpProducer) {
		boolean isNew = wsrpProducer.isNew();

		if (!(wsrpProducer instanceof WSRPProducerModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(wsrpProducer.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(wsrpProducer);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in wsrpProducer proxy " +
					invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom WSRPProducer implementation " +
				wsrpProducer.getClass());
		}

		WSRPProducerModelImpl wsrpProducerModelImpl = (WSRPProducerModelImpl)wsrpProducer;

		if (Validator.isNull(wsrpProducer.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			wsrpProducer.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (wsrpProducer.getCreateDate() == null)) {
			if (serviceContext == null) {
				wsrpProducer.setCreateDate(now);
			}
			else {
				wsrpProducer.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!wsrpProducerModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				wsrpProducer.setModifiedDate(now);
			}
			else {
				wsrpProducer.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (wsrpProducer.isNew()) {
				session.save(wsrpProducer);

				wsrpProducer.setNew(false);
			}
			else {
				wsrpProducer = (WSRPProducer)session.merge(wsrpProducer);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!WSRPProducerModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { wsrpProducerModelImpl.getUuid() };

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(_finderPathWithoutPaginationFindByUuid,
				args);

			args = new Object[] {
					wsrpProducerModelImpl.getUuid(),
					wsrpProducerModelImpl.getCompanyId()
				};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(_finderPathWithoutPaginationFindByUuid_C,
				args);

			args = new Object[] { wsrpProducerModelImpl.getCompanyId() };

			finderCache.removeResult(_finderPathCountByCompanyId, args);
			finderCache.removeResult(_finderPathWithoutPaginationFindByCompanyId,
				args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(_finderPathWithoutPaginationFindAll,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((wsrpProducerModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByUuid.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						wsrpProducerModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(_finderPathWithoutPaginationFindByUuid,
					args);

				args = new Object[] { wsrpProducerModelImpl.getUuid() };

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(_finderPathWithoutPaginationFindByUuid,
					args);
			}

			if ((wsrpProducerModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						wsrpProducerModelImpl.getOriginalUuid(),
						wsrpProducerModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(_finderPathWithoutPaginationFindByUuid_C,
					args);

				args = new Object[] {
						wsrpProducerModelImpl.getUuid(),
						wsrpProducerModelImpl.getCompanyId()
					};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(_finderPathWithoutPaginationFindByUuid_C,
					args);
			}

			if ((wsrpProducerModelImpl.getColumnBitmask() &
					_finderPathWithoutPaginationFindByCompanyId.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						wsrpProducerModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(_finderPathWithoutPaginationFindByCompanyId,
					args);

				args = new Object[] { wsrpProducerModelImpl.getCompanyId() };

				finderCache.removeResult(_finderPathCountByCompanyId, args);
				finderCache.removeResult(_finderPathWithoutPaginationFindByCompanyId,
					args);
			}
		}

		entityCache.putResult(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerImpl.class, wsrpProducer.getPrimaryKey(), wsrpProducer,
			false);

		clearUniqueFindersCache(wsrpProducerModelImpl, false);
		cacheUniqueFindersCache(wsrpProducerModelImpl);

		wsrpProducer.resetOriginalValues();

		return wsrpProducer;
	}

	/**
	 * Returns the wsrp producer with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the wsrp producer
	 * @return the wsrp producer
	 * @throws NoSuchProducerException if a wsrp producer with the primary key could not be found
	 */
	@Override
	public WSRPProducer findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProducerException {
		WSRPProducer wsrpProducer = fetchByPrimaryKey(primaryKey);

		if (wsrpProducer == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProducerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return wsrpProducer;
	}

	/**
	 * Returns the wsrp producer with the primary key or throws a {@link NoSuchProducerException} if it could not be found.
	 *
	 * @param wsrpProducerId the primary key of the wsrp producer
	 * @return the wsrp producer
	 * @throws NoSuchProducerException if a wsrp producer with the primary key could not be found
	 */
	@Override
	public WSRPProducer findByPrimaryKey(long wsrpProducerId)
		throws NoSuchProducerException {
		return findByPrimaryKey((Serializable)wsrpProducerId);
	}

	/**
	 * Returns the wsrp producer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the wsrp producer
	 * @return the wsrp producer, or <code>null</code> if a wsrp producer with the primary key could not be found
	 */
	@Override
	public WSRPProducer fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
				WSRPProducerImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		WSRPProducer wsrpProducer = (WSRPProducer)serializable;

		if (wsrpProducer == null) {
			Session session = null;

			try {
				session = openSession();

				wsrpProducer = (WSRPProducer)session.get(WSRPProducerImpl.class,
						primaryKey);

				if (wsrpProducer != null) {
					cacheResult(wsrpProducer);
				}
				else {
					entityCache.putResult(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
						WSRPProducerImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
					WSRPProducerImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return wsrpProducer;
	}

	/**
	 * Returns the wsrp producer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param wsrpProducerId the primary key of the wsrp producer
	 * @return the wsrp producer, or <code>null</code> if a wsrp producer with the primary key could not be found
	 */
	@Override
	public WSRPProducer fetchByPrimaryKey(long wsrpProducerId) {
		return fetchByPrimaryKey((Serializable)wsrpProducerId);
	}

	@Override
	public Map<Serializable, WSRPProducer> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WSRPProducer> map = new HashMap<Serializable, WSRPProducer>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WSRPProducer wsrpProducer = fetchByPrimaryKey(primaryKey);

			if (wsrpProducer != null) {
				map.put(primaryKey, wsrpProducer);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
					WSRPProducerImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (WSRPProducer)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_WSRPPRODUCER_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (WSRPProducer wsrpProducer : (List<WSRPProducer>)q.list()) {
				map.put(wsrpProducer.getPrimaryKeyObj(), wsrpProducer);

				cacheResult(wsrpProducer);

				uncachedPrimaryKeys.remove(wsrpProducer.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
					WSRPProducerImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the wsrp producers.
	 *
	 * @return the wsrp producers
	 */
	@Override
	public List<WSRPProducer> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the wsrp producers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of wsrp producers
	 * @param end the upper bound of the range of wsrp producers (not inclusive)
	 * @return the range of wsrp producers
	 */
	@Override
	public List<WSRPProducer> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the wsrp producers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of wsrp producers
	 * @param end the upper bound of the range of wsrp producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of wsrp producers
	 */
	@Override
	public List<WSRPProducer> findAll(int start, int end,
		OrderByComparator<WSRPProducer> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the wsrp producers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of wsrp producers
	 * @param end the upper bound of the range of wsrp producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of wsrp producers
	 */
	@Override
	public List<WSRPProducer> findAll(int start, int end,
		OrderByComparator<WSRPProducer> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = _finderPathWithoutPaginationFindAll;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<WSRPProducer> list = null;

		if (retrieveFromCache) {
			list = (List<WSRPProducer>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_WSRPPRODUCER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WSRPPRODUCER;

				if (pagination) {
					sql = sql.concat(WSRPProducerModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WSRPProducer>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WSRPProducer>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the wsrp producers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WSRPProducer wsrpProducer : findAll()) {
			remove(wsrpProducer);
		}
	}

	/**
	 * Returns the number of wsrp producers.
	 *
	 * @return the number of wsrp producers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(_finderPathCountAll,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WSRPPRODUCER);

				count = (Long)q.uniqueResult();

				finderCache.putResult(_finderPathCountAll, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return WSRPProducerModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the wsrp producer persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
				WSRPProducerModelImpl.FINDER_CACHE_ENABLED,
				WSRPProducerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
				WSRPProducerModelImpl.FINDER_CACHE_ENABLED,
				WSRPProducerImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
				new String[0]);

		_finderPathCountAll = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
				WSRPProducerModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
				new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
				WSRPProducerModelImpl.FINDER_CACHE_ENABLED,
				WSRPProducerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByUuid",
				new String[] {
					String.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
				WSRPProducerModelImpl.FINDER_CACHE_ENABLED,
				WSRPProducerImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
				new String[] { String.class.getName() },
				WSRPProducerModelImpl.UUID_COLUMN_BITMASK |
				WSRPProducerModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
				WSRPProducerModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
				new String[] { String.class.getName() });

		_finderPathFetchByUUID_G = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
				WSRPProducerModelImpl.FINDER_CACHE_ENABLED,
				WSRPProducerImpl.class, FINDER_CLASS_NAME_ENTITY,
				"fetchByUUID_G",
				new String[] { String.class.getName(), Long.class.getName() },
				WSRPProducerModelImpl.UUID_COLUMN_BITMASK |
				WSRPProducerModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
				WSRPProducerModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
				new String[] { String.class.getName(), Long.class.getName() });

		_finderPathWithPaginationFindByUuid_C = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
				WSRPProducerModelImpl.FINDER_CACHE_ENABLED,
				WSRPProducerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByUuid_C",
				new String[] {
					String.class.getName(), Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
				WSRPProducerModelImpl.FINDER_CACHE_ENABLED,
				WSRPProducerImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
				new String[] { String.class.getName(), Long.class.getName() },
				WSRPProducerModelImpl.UUID_COLUMN_BITMASK |
				WSRPProducerModelImpl.COMPANYID_COLUMN_BITMASK |
				WSRPProducerModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
				WSRPProducerModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
				new String[] { String.class.getName(), Long.class.getName() });

		_finderPathWithPaginationFindByCompanyId = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
				WSRPProducerModelImpl.FINDER_CACHE_ENABLED,
				WSRPProducerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByCompanyId",
				new String[] {
					Long.class.getName(),
					
				Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByCompanyId = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
				WSRPProducerModelImpl.FINDER_CACHE_ENABLED,
				WSRPProducerImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
				new String[] { Long.class.getName() },
				WSRPProducerModelImpl.COMPANYID_COLUMN_BITMASK |
				WSRPProducerModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByCompanyId = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
				WSRPProducerModelImpl.FINDER_CACHE_ENABLED, Long.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
				new String[] { Long.class.getName() });
	}

	public void destroy() {
		entityCache.removeCache(WSRPProducerImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_WSRPPRODUCER = "SELECT wsrpProducer FROM WSRPProducer wsrpProducer";
	private static final String _SQL_SELECT_WSRPPRODUCER_WHERE_PKS_IN = "SELECT wsrpProducer FROM WSRPProducer wsrpProducer WHERE wsrpProducerId IN (";
	private static final String _SQL_SELECT_WSRPPRODUCER_WHERE = "SELECT wsrpProducer FROM WSRPProducer wsrpProducer WHERE ";
	private static final String _SQL_COUNT_WSRPPRODUCER = "SELECT COUNT(wsrpProducer) FROM WSRPProducer wsrpProducer";
	private static final String _SQL_COUNT_WSRPPRODUCER_WHERE = "SELECT COUNT(wsrpProducer) FROM WSRPProducer wsrpProducer WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "wsrpProducer.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WSRPProducer exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No WSRPProducer exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(WSRPProducerPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}