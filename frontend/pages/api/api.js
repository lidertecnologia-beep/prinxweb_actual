import Axios from "axios";

export const getUrlPayara = Axios.create({
  baseURL: process.env.NEXT_PUBLIC_ENV_LOCAL_PATH_API,
});

export const getUrlSpring = Axios.create({
  baseURL: process.env.NEXT_PUBLIC_ENV_LOCAL_PATH_API_SPRING,
});

const typeAuth = {
  Basic: (us, pw) =>
    Buffer.from(`${us}:${pw}`, "utf8").toString(
      process.env.NEXT_PUBLIC_ENDPOINT_TYPE_ENCODE
    ),
};

const requestGet = async (token, endPoint) => {
  console.log("requestGet pass 1", token, endPoint);
  let data = {};
  try {
    const url = `${endPoint}`;
    console.log("requestGet", url);
    const res = await Axios.get(url, { headers: { token } });
    data = res.data;
  } catch (err) {
    console.error("api requestGet", `${err} endPoint:${endPoint}`);
  }
  return data;
};

export const getDataTest = async (endPoint) => {
  let data = {};
  try {
    const url = `${endPoint}`;
    const res = await Axios.get(url);
    data = res.data;
  } catch (err) {
    console.error("api getDataTest", `${err} endPoint:${endPoint}`);
  }
  return data;
};

export const requestInsert = async (token, endPoint, dataForm) => {
  let data = {};
  try {
    const res = await Axios.post(endPoint, dataForm, { headers: { token } });
    data = res.data;
  } catch (err) {
    console.error("api requestInsert", `${err} endPoint:${endPoint}`);
  }
  return data;
};

export const requestPost = async (endPoint, dataForm) => {
  let data = {};
  try {
    const res = await Axios.post(endPoint, dataForm);
    data = res.data;
  } catch (err) {
    console.error("api requestPost", `${err} endPoint:${endPoint}`);
  }
  return data;
};

export const requestPut = async (token, endPoint, dataForm) => {
  let data = {};
  try {
    const res = await Axios.put(endPoint, dataForm, { headers: { token } });
    data = res.data;
  } catch (err) {
    console.error("api requestPut", `${err} endPoint:${endPoint}`);
  }
  return data;
};

export const requestSendingFilesPost = async (token, endPoint, dataForm) => {
  let data = {};
  try {
    const res = await Axios.post(endPoint, dataForm, { headers: { token } });
    data = res.data;
  } catch (err) {
    console.error("api requestSendingFilesPost", `${err} endPoint:${endPoint}`);
  }
  return data;
};

const requestDelete = async (token, endPoint) => {
  let data = {};
  try {
    const url = `${endPoint}`;
    const res = await Axios.delete(url, { headers: { token } });
    data = res.data;
  } catch (err) {
    console.error("api requestDelete", `${err} endPoint:${endPoint}`);
  }
  return data;
};

export const requestDelete2 = async (token, endPoint) => {
  let data = {};
  try {
    const url = `${endPoint}`;
    const res = await Axios.delete(url, { headers: { token } });
    data = res.data;
  } catch (err) {
    console.error("api requestDelete2", `${err} endPoint:${endPoint}`);
  }
  return data;
};

export const getBlobFetch = async (token, endPoint) => {
  try {
    const url = endPoint;
    const headers = { token };
    const options = { method: "GET", headers };
    const response = await fetch(url, options);
    return await response.blob();
  } catch (err) {
    console.error("api getBlobFetch", `${err} endPoint:${endPoint}`);
  }
};

export const fetchNext = async (url, token, cache = "no-store") => {
  try {
    const objHeader = {
      headers: {
        token,
        next: cache,
      },
    };
    const result = await fetch(url, objHeader);
    const data = await result.json();
    console.log('fetchNext', data)
    return data;
  } catch (error) {
    throw new Error(`fetchNext error:${error}`);
  }
};

export const deleteAnexsoli = (token, endPoint) =>
  requestDelete(token, endPoint);

export const insertPost = (token, endPoint, dataForm) =>
  requestInsert(token, endPoint, dataForm);

export const insertPost2 = (endPoint, dataForm) =>
  requestPost(endPoint, dataForm);

export const getPost = (endPoint, dataForm) => requestPost(endPoint, dataForm);

export const updatePut = (token, endPoint, dataForm) =>
  requestPut(token, endPoint, dataForm);

export const sendingFilesPost = async (token, endPoint, dataForm) => {
  await requestSendingFilesPost(token, endPoint, dataForm);
}

export const requestAuth = async (us, pw, endPoint) =>
  await Axios.post(
    endPoint,
    {},
    {
      headers: {
        Authorization: `${process.env.NEXT_PUBLIC_ENDPOINT_TYPE_AUTH} ${Buffer.from(`${us}:${pw}`, "utf8").toString(process.env.NEXT_PUBLIC_ENDPOINT_TYPE_ENCODE)}`,
      },
    }
  );

export const getDataListValo = (token, cliente, endPoint) =>
  requestGet(token, `${endPoint}/listVal?cliente=${cliente}`);

const getData = (token, endPoint) => requestGet(token, endPoint);

export default getData;
