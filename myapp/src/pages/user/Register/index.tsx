import Footer from '@/components/Footer';
import {register} from '@/services/ant-design-pro/api';
import {LockOutlined, UserOutlined,} from '@ant-design/icons';
import {LoginForm, ProFormText,} from '@ant-design/pro-components';
import {message, Tabs} from 'antd';
import React, {useState} from 'react';
import {FormattedMessage, history, SelectLang, useIntl} from 'umi';
import styles from './index.less';
import {PLANET_LINK, SYSTEM_LOGO} from "@/constants";


const Register: React.FC = () => {
  const [type, setType] = useState<string>('account');
  const intl = useIntl();



  const handleSubmit = async (values: API.RegisterParams) => {
    const { userPassword, checkPassword} = values;
    if(userPassword !== checkPassword) {
      message.error("两次输入的密码不一致");
      return;
    }


    try {
      // 注册
      const id = await register(values);
      if (id > 0) {
        const defaultLoginSuccessMessage = intl.formatMessage({
          id: 'pages.login.success',
          defaultMessage: '注册成功！',
        });
        message.success(defaultLoginSuccessMessage);
        /** 此方法会跳转到 redirect 参数所在的位置 */
        if (!history) return;
        const {query} = history.location;
        /**
        通过 const {query} = history.location; 这一行，它从当前页面的 history 对象中提取了 location 属性，
        然后从中解构出 query。这表示开发者可能希望在用户注册成功后，
        将之前页面的查询参数保留，并在重定向到登录页面时附加到新的 URL 中。
         */
        history.push({
          pathname: '/user/login',
          query,
        });
        return;
      } else {
        throw new Error(`register error id = ${id}`);
      }
    } catch (error) {
      const defaultLoginFailureMessage = intl.formatMessage({
        id: 'pages.login.failure',
        defaultMessage: '注册失败，请重试！',
      });
      message.error(defaultLoginFailureMessage);
    }
  };


  return (
    <div className={styles.container}>
      <div className={styles.lang} data-lang>
        {SelectLang && <SelectLang/>}
      </div>
      <div className={styles.content}>
        <LoginForm
          submitter={{
            searchConfig: {
              submitText: '注册'
            }
          }}
          logo={<img alt="logo" src={SYSTEM_LOGO}/>}
          title="sll's self learning program"
          subTitle={<a href={PLANET_LINK} target="_blank" rel="noreferrer"> sll forever love zjr </a>}
          initialValues={{
            autoLogin: true,
          }}


          onFinish={async (values) => {
            await handleSubmit(values as API.RegisterParams);
          }}
        >
          <Tabs activeKey={type} onChange={setType}>
            <Tabs.TabPane key="account" tab={'账号密码注册'}/>
          </Tabs>

          {type === 'account' && (
            <>
              <ProFormText
                name="userAccount"
                fieldProps={{
                  size: 'large',
                  prefix: <UserOutlined className={styles.prefixIcon}/>,
                }}
                placeholder="请输入账号"
                rules={[
                  {
                    required: true,
                    message: (
                      <FormattedMessage
                        id="pages.login.userAccount.required"
                        defaultMessage="用户是必填项!"
                      />
                    ),
                  },
                ]}
              />
              <ProFormText.Password
                name="userPassword"
                fieldProps={{
                  size: 'large',
                  prefix: <LockOutlined className={styles.prefixIcon}/>,
                }}
                placeholder='请输入密码'
                rules={[
                  {
                    required: true,
                    message: (
                      <FormattedMessage
                        id="pages.login.userPassword.required"
                        defaultMessage="密码是必填项！"
                      />
                    ),
                  },
                  {
                    min: 8,
                    type: 'string',
                    message: "长度不得少于8位",
                  }
                ]}
              />
              <ProFormText.Password
                name="checkPassword"
                fieldProps={{
                  size: 'large',
                  prefix: <LockOutlined className={styles.prefixIcon}/>,
                }}
                placeholder='请确认密码'
                rules={[
                  {
                    required: true,
                    message: (
                      <FormattedMessage
                        id="pages.login.userPassword.required"
                        defaultMessage="确认密码是必填项！"
                      />
                    ),
                  },
                  {
                    min: 8,
                    type: 'string',
                    message: "长度不得少于8位",
                  }
                ]}
              />
              <ProFormText
                name="planetCode"
                fieldProps={{
                  size: 'large',
                  prefix: <UserOutlined className={styles.prefixIcon}/>,
                }}
                placeholder="请输入请求编号"
                rules={[
                  {
                    required: true,
                    message: (
                      <FormattedMessage
                        id="pages.login.planetCode.required"
                        defaultMessage="星球编号是必填项!"
                      />
                    ),
                  },
                ]}
              />
            </>
          )}

        </LoginForm>
      </div>
      <Footer/>
    </div>
  );
};

export default Register;
